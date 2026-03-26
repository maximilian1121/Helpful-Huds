package ca.maximilian.hudmod.client.Config;

import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ModConfig extends Config {

    public ModConfig() {
        super(new ResourceLocation(ca.maximilian.hudmod.client.Utils.MOD_ID, "config"));
    }

    public boolean hudsAreVisible = true;

    public ValidatedBoolean bedrockCoordinateCounter = new ValidatedBoolean(true);
    public ValidatedBoolean bedrockDaysPlayedCounter = new ValidatedBoolean(true);
    public ValidatedBoolean bedrockTimeOfDayCounter = new ValidatedBoolean(false);
    public ValidatedBoolean hotbarCoordinateCounter = new ValidatedBoolean(false);
    public ValidatedBoolean compassHud = new ValidatedBoolean(false);

    @Override
    public int defaultPermLevel() {
        return 0;
    }

    @Override
    public @NotNull FileType fileType() {
        return FileType.JSONC;
    }

    @Override
    public @NotNull SaveType saveType() {
        return SaveType.SEPARATE;
    }
}