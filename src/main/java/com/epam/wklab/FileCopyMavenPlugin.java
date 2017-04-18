package com.epam.wklab;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Copying file from source to destination
 */
@Mojo( name = "filecopy")
public class FileCopyMavenPlugin {
    @Parameter(property = "src", defaultValue = "./input/my.properties")
    private String sourceFileName;
    @Parameter(property = "dst", defaultValue = "./output/my_new.properties")
    private String destinationFileName;
    @Parameter(property = "overwrite", defaultValue = "true")
    private boolean overwrite;

    public void execute() throws MojoExecutionException {
        try {
            if(overwrite) {
                copyOverwrite();
            } else {
                copyNoOverwrite();
            }
        } catch(SecurityException e) {
            throw new MojoExecutionException("Access rigths problems: " + e);
        }
    }

    private void copyOverwrite() throws SecurityException {
        Files.copy(sourceFileName, destinationFileName, COPY_ATTRIBUTES, REPLACE_EXISTING);
    }

    private void copyNoOverwrite() throws MojoExecutionException, SecurityException {
        try {
            Files.copy(sourceFileName, destinationFileName, COPY_ATTRIBUTES);
        } catch(FileAlreadyExistsException e) {
            throw new MojoExecutionException("Destination file already exists: " + e);
        }
    }



}
