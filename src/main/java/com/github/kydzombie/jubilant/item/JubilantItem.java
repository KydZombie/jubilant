package com.github.kydzombie.jubilant.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.gui.CustomTooltipProvider;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class JubilantItem extends TemplateItemBase implements CustomTooltipProvider {
    protected final String[] tooltip;

    public JubilantItem(Identifier identifier, boolean createDescription) {
        super(identifier);
        setTranslationKey(identifier.toString());

        if (createDescription && !TranslationStorage.getInstance().translate(this.getTranslationKey() + ".description").equals(this.getTranslationKey() + ".description")) {
            tooltip = new String[] {
                    TranslationStorage.getInstance().translate(this.getTranslationKey() + ".name"),
                    "  " + TranslationStorage.getInstance().translate(this.getTranslationKey() + ".description")
            };
        } else {
            tooltip = new String[] { TranslationStorage.getInstance().translate(this.getTranslationKey() + ".name") };
        }
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return tooltip;
    }
}
