import bpy
import bpy_extras
import struct

bl_info = {
    "name": "MCExporter",
    "description": "Animation exporter for MC",
    "author": "56738",
    "version": (1, 0),
    "blender": (3, 5, 0),
    "location": "File > Export",
}


def write_matrix(file, value):
    for col in value.col:
        for cell in col:
            file.write(struct.pack('>d', cell))


def write_int(file, value):
    file.write(struct.pack('>I', value))


def write_str(file, value):
    encoded = value.encode('utf-8')
    file.write(struct.pack('>H', len(encoded)))
    file.write(encoded)


class Channel:
    def __init__(self, name: str, kind: str):
        self.name = name
        self.kind = kind

    def export(self, file, mat):
        pass


class Element:
    def __init__(self, name: str):
        self.name = name
        self.channels = []

    def add_channel(self, channel: Channel):
        self.channels.append(channel)


class MatrixChannel(Channel):
    def __init__(self, name: str):
        super().__init__(name, 'mat4')

    def get_mat(self):
        pass

    def export(self, file, mat):
        write_matrix(file, mat @ self.get_mat())


class ObjectPositionChannel(MatrixChannel):
    def __init__(self, name, obj):
        super().__init__(name)
        self.obj = obj

    def get_mat(self):
        return self.obj.matrix_world


class BonePositionChannel(ObjectPositionChannel):
    def __init__(self, name, obj, bone):
        super().__init__(name)
        self.obj = obj
        self.bone = bone
        self.pose_bone = obj.pose.bones[bone.name]

    def get_mat(self):
        return super().get_mat() @ self.bone.matrix


def write_elements(file, elements):
    write_int(file, len(elements))
    for e in elements:
        write_str(file, e.name)
        write_int(file, len(e.channels))
        for ch in e.channels:
            write_str(file, ch.name)
            write_str(file, ch.kind)


def write_frame(file, mat, elements):
    for e in elements:
        for ch in e.channels:
            ch.export(file, mat)


def write_frames(file, mat, elements, scene, frame_start, frame_end):
    write_int(file, frame_end - frame_start + 1)
    frame_current = scene.frame_current
    for frame in range(frame_start, frame_end + 1):
        scene.frame_set(frame)
        write_frame(file, mat, elements)
    scene.frame_set(frame_current)


def write_anim(file, scene, frame_start, frame_end):
    elements = []

    for obj in scene.objects:
        if 'mc' in obj:
            e = Element(obj['mc'])
            e.add_channel(ObjectPositionChannel('matrix', obj))
            elements.append(e)

        if obj.type == 'ARMATURE':
            for bone in obj.data.bones:
                if 'mc' in bone:
                    e = Element(bone['mc'])
                    e.add_channel(BonePositionChannel('matrix', obj, bone))
                    elements.append(e)

    global_mat = bpy_extras.io_utils.axis_conversion(to_forward='Z', to_up='Y').to_4x4()

    file.write(b'MCANIM01')
    write_elements(file, elements)
    write_frames(file, global_mat, elements, scene, frame_start, frame_end)


def export_anim(context, filepath):
    with open(filepath, 'wb') as file:
        scene = context.scene
        write_anim(file, scene, scene.frame_start, scene.frame_end)
    return {'FINISHED'}


class ExportMCAnimation(bpy.types.Operator, bpy_extras.io_utils.ExportHelper):
    """Save an animation"""
    bl_idname = 'export_anim.mcanim'
    bl_label = "Export MC animation"

    filename_ext = '.mcanim'

    def execute(self, context):
        keywords = self.as_keywords(
            ignore=(
                'check_existing',
            )
        )
        return export_anim(context, **keywords)


def menu_func_export(self, context):
    self.layout.operator(ExportMCAnimation.bl_idname, text="Animation (.mcanim)")


def register():
    bpy.utils.register_class(ExportMCAnimation)
    bpy.types.TOPBAR_MT_file_export.append(menu_func_export)


def unregister():
    bpy.utils.unregister_class(ExportMCAnimation)
    bpy.types.TOPBAR_MT_file_export.remove(menu_func_export)


if __name__ == '__main__':
    register()
