package com.github.kydzombie.jubilant.spell;

import com.github.kydzombie.jubilant.Jubilant;

public class VanishingBlockSpell extends BlockPlacingSpell {
    public VanishingBlockSpell(int cost) {
        super(cost, Jubilant.VANISHING_BLOCK, false);
    }

    @Override
    public String getName() {
        return "jubilant:vanishing";
    }
}
