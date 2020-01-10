package com.epam.lemon;

import com.epam.lemon.converter.CobolConverter;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.RecordIterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CobolConverterTest {

  private CobolConverter cobolConverter;
  private CopybookParser copybookParser;

  @Before
  public void setUp() {
    cobolConverter = new CobolConverter();
    copybookParser = new CopybookParser();
  }

  @Test
  public void convertFile_fromAsciiToEbcdic() throws IOException {
    Copybook copybook = copybookParser.parse(
        new CopybookStatementIterator(Files.readAllBytes(Paths.get("src/test/resources/PERSINFO.cpy"))));
    RecordIterator dataFileIterator = new RecordIterator(copybook,
        Files.readAllBytes(Paths.get("src/test/resources/asc_TEST")));

    byte[] convertedDataset = cobolConverter.convert(dataFileIterator, Encoding.ASCII, Encoding.EBCDIC);

    byte[] expectedArray = {57, 57, -63, -63, -63, 57, 56, -63, -63, -62};
    Assert.assertArrayEquals(expectedArray, convertedDataset);
  }

  @Test
  public void convertFile_fromEbcdicToAscii() throws IOException {
    Copybook copybook = copybookParser.parse(
        new CopybookStatementIterator(Files.readAllBytes(Paths.get("src/test/resources/PERSINFO.cpy"))));
    RecordIterator dataFileIterator = new RecordIterator(copybook,
        Files.readAllBytes(Paths.get("src/test/resources/ebc_TEST")));

    byte[] convertedDataset = cobolConverter.convert(dataFileIterator, Encoding.EBCDIC, Encoding.ASCII);

    byte[] expectedArray = {57, 57, 65, 65, 65, 57, 56, 65, 65, 66};
    Assert.assertArrayEquals(expectedArray, convertedDataset);
  }

  @Test
  public void convertFile_emptyFieldWithSpaces() throws IOException {
    Copybook copybook = copybookParser.parse(
        new CopybookStatementIterator(Files.readAllBytes(Paths.get("src/test/resources/PERSINFO.cpy"))));
    RecordIterator dataFileIterator = new RecordIterator(copybook,
        Files.readAllBytes(Paths.get("src/test/resources/emp_TEST")));

    byte[] convertedDataset = cobolConverter.convert(dataFileIterator, Encoding.ASCII, Encoding.EBCDIC);

    byte[] expectedArray = {57, 57, -63, -63, -63, 57, 56, 64, 64, 64};
    Assert.assertArrayEquals(expectedArray, convertedDataset);
  }

  @Test(expected = InvalidDataException.class)
  public void convertFile_emptyField() throws IOException {
    Copybook copybook = copybookParser.parse(
        new CopybookStatementIterator(Files.readAllBytes(Paths.get("src/test/resources/PERSINFO.cpy"))));
    RecordIterator dataFileIterator = new RecordIterator(copybook,
        Files.readAllBytes(Paths.get("src/test/resources/empfield_TEST")));

    cobolConverter.convert(dataFileIterator, Encoding.ASCII, Encoding.EBCDIC);
  }
}