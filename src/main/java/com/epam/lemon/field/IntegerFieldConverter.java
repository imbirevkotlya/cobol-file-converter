package com.epam.lemon.field;

import com.epam.lemon.statement.StatementType;

/**
 * Class represents the COBOL integer numeric field conversion approach.
 */
public class IntegerFieldConverter extends AbstractFieldConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isConvertedField() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatementType getStatementType() {
        return StatementType.INTEGER_STATEMENT;
    }
}
