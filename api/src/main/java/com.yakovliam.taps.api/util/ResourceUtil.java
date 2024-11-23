package com.yakovliam.taps.api.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourceUtil {
  /**
   * for all elements of java.class.path get a Collection of resources Pattern
   * pattern = Pattern.compile(".*"); gets all resources
   *
   * @param pattern the pattern to match
   * @return the resources in the order they are found
   */
  public static Collection<String> getResources(final Pattern pattern) {
    final ArrayList<String> returnValue = new ArrayList<>();
    final String classPath = System.getProperty("java.class.path", ".");
    final String[] classPathElements = classPath.split(File.pathSeparator);
    for (final String element : classPathElements) {
      returnValue.addAll(getResources(element, pattern));
    }
    return returnValue;
  }

  private static Collection<String> getResources(final String element, final Pattern pattern) {
    final ArrayList<String> returnValue = new ArrayList<>();
    final File file = new File(element);
    if (file.isDirectory()) {
      returnValue.addAll(getResourcesFromDirectory(file, pattern));
    } else {
      returnValue.addAll(getResourcesFromJarFile(file, pattern));
    }
    return returnValue;
  }

  private static Collection<String> getResourcesFromJarFile(final File file,
                                                            final Pattern pattern) {
    final ArrayList<String> returnValue = new ArrayList<String>();
    ZipFile zf;
    try {
      zf = new ZipFile(file);
    } catch (final IOException e) {
      throw new Error(e);
    }
    final Enumeration<? extends ZipEntry> e = zf.entries();
    while (e.hasMoreElements()) {
      final ZipEntry ze = e.nextElement();
      final String fileName = ze.getName();
      final boolean accept = pattern.matcher(fileName).matches();
      if (accept) {
        returnValue.add(fileName);
      }
    }
    try {
      zf.close();
    } catch (final IOException e1) {
      throw new Error(e1);
    }
    return returnValue;
  }

  private static Collection<String> getResourcesFromDirectory(final File directory,
                                                              final Pattern pattern) {
    final ArrayList<String> returnValue = new ArrayList<>();
    final File[] fileList = directory.listFiles();

    if (fileList == null) {
      return returnValue;
    }

    for (final File file : fileList) {
      if (file.isDirectory()) {
        returnValue.addAll(getResourcesFromDirectory(file, pattern));
      } else {
        try {
          final String fileName = file.getCanonicalPath();
          final boolean accept = pattern.matcher(fileName).matches();
          if (accept) {
            returnValue.add(fileName);
          }
        } catch (final IOException e) {
          throw new Error(e);
        }
      }
    }
    return returnValue;
  }
}
