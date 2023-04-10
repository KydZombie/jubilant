package com.github.kydzombie.jubilant.spell;

import com.github.kydzombie.jubilant.Jubilant;

public class LightBlockSpell extends BlockPlacingSpell {
    public LightBlockSpell(int cost) {
        super(cost, Jubilant.LIGHT_BLOCK, false);
    }

    @Override
    public String getName() {
        return "jubilant:lightPlace";
    }
}
