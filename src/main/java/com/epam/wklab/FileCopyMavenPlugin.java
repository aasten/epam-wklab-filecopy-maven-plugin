package com.epam.wklab;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Copying file from source to destination
 */
@Mojo( name = "filecopy")
public class FileCopyMavenPlugin extends AbstractMojo {
    @Parameter(defaultValue = "./input/my.properties")
    private String src;
    @Parameter(defaultValue = "./output/my_new.properties")
    private String dst;
    @Parameter(defaultValue = "true")
    private boolean overwrite;

    private Path srcPath;
    private Path dstPath;


    public void execute() throws MojoExecutionException {
        srcPath = Paths.get(src);
        dstPath = Paths.get(dst);
        try {
            if(overwrite) {
                copyOverwrite();
            } else {
                copyNoOverwrite();
            }
        } catch(SecurityException e) {
            throw new MojoExecutionException("Access rigths problems: " + e);
        } catch(IOException e) {
            throw new MojoExecutionException("I/O error occurs: " + e);
        }
    }

    private void copyOverwrite() throws SecurityException, IOException {
        Files.copy(srcPath, dstPath, COPY_ATTRIBUTES, REPLACE_EXISTING);
    }

    private void copyNoOverwrite() throws MojoExecutionException, SecurityException, IOException {
        try {
            Files.copy(srcPath, dstPath, COPY_ATTRIBUTES);
        } catch(FileAlreadyExistsException e) {
            throw new MojoExecutionException("Destination file already exists: " + e);
        }
    }



}
