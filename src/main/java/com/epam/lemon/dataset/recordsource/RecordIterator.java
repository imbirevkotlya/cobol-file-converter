package com.epam.lemon.dataset.recordsource;

import com.epam.lemon.copybook.Copybook;
import java.util.Iterator;

public interface RecordIterator extends Iterator<byte[]> {

  Copybook getRecordStructure();

  Integer getRecordLength();

}