package com.epam.lemon.record;

import com.epam.lemon.Convertable;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.encoding.Encoding;
import com.epam.lemon.field.Field;
import java.util.Collections;
import java.util.List;

/**
 * The class represents the mainframe record as a container for the information.
 */
public class Record implements Convertable {

  private final List<Field> fields;

  public Record(Copybook recordStructure, Integer recordLength, byte[] value) {
    fields = initFields();
  }

  private List<Field> initFields() {
    return Collections.emptyList();
  }

  @Override
  public void convert(Encoding targetEncoding) {
    for (Field field : fields) {
      field.convert(targetEncoding);
    }
  }
}
