package com.epam.lemon.converter;

import com.epam.lemon.dataset.Dataset;
import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.field.AlphanumericFieldConverter;
import com.epam.lemon.field.FieldConverter;
import com.epam.lemon.field.IntegerFieldConverter;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.Record;
import com.epam.lemon.record.RecordIterator;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CobolConverter {

  private final List<FieldConverter> fieldConverters;

  public CobolConverter() {
    fieldConverters = new ArrayList<>();
    fieldConverters.add(new IntegerFieldConverter());
    fieldConverters.add(new AlphanumericFieldConverter());
  }

  public byte[] convert(RecordIterator dataFileIterator, Encoding sourceEncoding,  Encoding targetEncoding) throws InvalidDataException {
    Dataset dataset = new Dataset(dataFileIterator.getValue().length);
    while (dataFileIterator.hasNext()) {
      Record record = dataFileIterator.next();
      record = convertRecord(record, sourceEncoding, targetEncoding);
      dataset.addRecord(record);
    }
    return dataset.getDataFile();
  }

  private Record convertRecord(Record record, Encoding sourceEncoding, Encoding targetEncoding) throws InvalidDataException {
    byte[] recordValue = record.getValue();
    Record resultRecord = new Record(record.getRecordStructure());
    ByteBuffer buffer = ByteBuffer.allocate(recordValue.length);
    int startPosition = 0;
    for (DataDeclarationCobolStatement cobolStatement : record.getRecordStructure().getCobolStatements()) {
      if (cobolStatement.getStatementType().equals(StatementType.GROUP_STATEMENT)) {
        GroupDataDeclarationCobolStatement groupStatement = (GroupDataDeclarationCobolStatement) cobolStatement;
        List<DataDeclarationCobolStatement> childrenStatements = groupStatement.getChildrenStatements();
        for (DataDeclarationCobolStatement childStatement : childrenStatements) {
          RegularDataDeclarationCobolStatement regularStatement = (RegularDataDeclarationCobolStatement) childStatement;
          Integer fieldLength = regularStatement.getLength();
          byte[] fieldValue = Arrays.copyOfRange(recordValue, startPosition, startPosition + fieldLength);
          startPosition += fieldLength;
          for (FieldConverter fieldConverter : fieldConverters) {
            if (fieldConverter.getStatementType().equals(regularStatement.getStatementType())) {
              byte[] resultValue = fieldConverter.convertValue(fieldValue, sourceEncoding, targetEncoding);
              buffer.put(resultValue);
            }
          }
        }
      }
      else {
        RegularDataDeclarationCobolStatement regularStatement = (RegularDataDeclarationCobolStatement) cobolStatement;
        Integer fieldLength = regularStatement.getLength();
        byte[] fieldValue = Arrays.copyOfRange(recordValue, startPosition, startPosition + fieldLength);
        startPosition += fieldLength;
        for (FieldConverter fieldConverter : fieldConverters) {
          if (fieldConverter.getStatementType().equals(regularStatement.getStatementType())) {
            byte[] resultValue = fieldConverter.convertValue(fieldValue, sourceEncoding, targetEncoding);
            buffer.put(resultValue);
          }
        }
      }
    }
    resultRecord.setValue(buffer.array());
    return resultRecord;
  }
}
