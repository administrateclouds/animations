package cloud.polars.animations.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class Animations {
    final class Provider {
        private static Animations instance;

        public Provider() {}

        @NotNull
        public static Animations getInstance() {
            return instance;
        }

        @ApiStatus.Internal
        public static void setInstance(Animations instance) {
            Provider.instance = instance;
        }
    }
}
