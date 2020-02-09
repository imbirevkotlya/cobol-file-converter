package com.epam.lemon;

import com.epam.lemon.encoding.Encoding;

/**
 * The main contract interface of the library, specified, that class can be converted into the
 * desired encoding (specified by the caller).
 */
public interface Convertable {

  /**
   * The main interface method, provided ability to convert the state of the object to specified
   * encoding.
   * @param targetEncoding is a target encoding (charset) to convert the object
   * @throws com.epam.lemon.exception.ConversionException if something went wrong with conversion
   */
  void convert(Encoding targetEncoding);

}
