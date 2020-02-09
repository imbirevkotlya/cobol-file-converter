package com.epam.lemon.record;

import com.epam.lemon.Convertable;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.encoding.Encoding;
import com.epam.lemon.field.Field;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the mainframe record as a container for the information.
 */
public class Record implements Convertable {

  private final List<Field> fields = new ArrayList<>();
  private final List<Field> availableFields = new ArrayList<>();

  public Record(Copybook recordStructure, byte[] recordValue) {
    initFields(recordValue, recordStructure);
  }

  private void initFields(byte[] value, Copybook recordStructure) {
    List<DataDeclarationCobolStatement> fieldDeclarations = recordStructure.getCobolStatements();
    for (DataDeclarationCobolStatement fieldDeclaration : fieldDeclarations) {
      StatementType fieldType = fieldDeclaration.getStatementType();
      for (Field availableField : availableFields) {
        if (availableField.getCorrespondingStatementType().equals(fieldType)) {
          fields.add(availableField.buildField());
        }
      }
    }
  }

  @Override
  public void convert(Encoding targetEncoding) {
    for (Field field : fields) {
      field.convert(targetEncoding);
    }
  }
}
