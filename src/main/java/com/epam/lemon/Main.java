package com.epam.lemon;

import com.epam.lemon.converter.CobolConverter;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.RecordIterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

  public static void main(String[] args) throws IOException {
    Copybook copybook = new CopybookParser().parse(
            new CopybookStatementIterator(Files.readAllBytes(Path.of("src/main/resources/PERSINFO.cpy"))));
    byte[] convert = new CobolConverter().convert(new RecordIterator(copybook,
            Files.readAllBytes(Path.of("src/main/resources/TEST"))), Encoding.ASCII, Encoding.EBCDIC);
    Files.write(Path.of("src/main/resources/ebc_TEST"), convert);
  }
}
