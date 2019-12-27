package com.epam.lemon.field;

import com.epam.lemon.statement.StatementType;

public class IntegerFieldConverter extends AbstractFieldConverter {

    @Override
    protected boolean isConvertedField() {
        return false;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.INTEGER_STATEMENT;
    }
}
