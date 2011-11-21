/* FileIterator.java
 * 
 ******************************************************************************
 *
 * Created: Oct 10, 2011
 * Character encoding: UTF-8
 * 
 * Copyright (c) 2011 - XIAM Solutions B.V. The Netherlands, http://www.xiam.nl
 * 
 ********************************* LICENSE ************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.infomas.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * {@code FileIterator} enables iteration over all files in a directory and all
 * its sub directories.
 * <br/>
 * Usage:
 * <pre>
 * FileIterator iter = new FileIterator(new File("./src"));
 * File f;
 * while ((f = iter.next()) != null) {
 *     // do something witgh f
 * }
 * </pre>
 * 
 * @author <a href="mailto:rmuller@xiam.nl">Ronald K. Muller</a>
 * @since annotation-detector 3.0.0
 */
public final class FileIterator {

    private final LinkedList<File> stack = new LinkedList<File>();
    private File current;
    
    /**
     * Create a new {@code FileIterator} using the specified 'filesOrDirectories' as root.
     * <br/>
     * If 'filesOrDirectories' contains a file, the iterator just returns that single file. 
     * If 'filesOrDirectories' contains a directory, all files in that directory 
     * and its sub directories are returned (depth first).
     * 
     * @param filesOrDirectories Zero or more {@link File} objects, which are iterated
     * in the specified order (depth first)
     */
    public FileIterator(final File... filesOrDirectories) {
        addReverse(filesOrDirectories);
    }

    /**
     * Return the last returned file or {@code null} if no more files are available.
     * 
     * @see #next()
     */
    public File getFile() {
        return current;
    }
    
    /**
     * Return the next {@link File} object or {@code null} if no more files are
     * available.
     * 
     * @see #getFile()
     */
    public File next() throws IOException {
        if (stack.isEmpty()) {
            return null;
        } else {
            current = stack.removeLast();
            if (current.isDirectory()) {
                addReverse(current.listFiles());
                return next();
            } else {
                return current;
            }
        }
    }

    /**
     * Add the specified files in reverse order.
     */
    private void addReverse(final File[] files) {
        for (int i = files.length - 1; i >=0; --i) {
            stack.add(files[i]);
        }
    }

}
