package com.haomai.yyq.yyq.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by YYQ on 2017/12/4.
 */

public enum ECIcon implements Icon {
    icon_scan('\ue65b'),
    icon_pay('\ue646')
    ;

    char character;

    ECIcon(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
