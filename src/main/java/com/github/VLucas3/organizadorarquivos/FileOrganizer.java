package com.github.VLucas3.organizadorarquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileOrganizer {
    private static final Map<String, String> EXTENSION_FOLDERS = new HashMap<>();

    static {
        EXTENSION_FOLDERS.put(".pdf", "PDFs");
        EXTENSION_FOLDERS.put(".jpg", "Imagens");
        EXTENSION_FOLDERS.put(".png", "Imagens");
        EXTENSION_FOLDERS.put(".docx", "Documentos");
        EXTENSION_FOLDERS.put(".zip", "Compactados");
    }

    public static void organizeFiles(String sourcePath) throws IOException {
        Path sourceDir = Paths.get(sourcePath);
        
        if (!Files.exists(sourceDir)) {
            System.out.println("Diretório não encontrado!");
            return;
        }

        Files.list(sourceDir).forEach(file -> {
            if (!Files.isDirectory(file)) {
                String fileName = file.getFileName().toString();
                int lastDotIndex = fileName.lastIndexOf('.');
                
                if (lastDotIndex > 0) {
                    String extension = fileName.substring(lastDotIndex).toLowerCase();
                    String folderName = EXTENSION_FOLDERS.getOrDefault(extension, "Outros");
                    
                    try {
                        Path targetDir = sourceDir.resolve(folderName);
                        if (!Files.exists(targetDir)) {
                            Files.createDirectory(targetDir);
                        }
                        Files.move(file, targetDir.resolve(file.getFileName()));
                        System.out.println("Movido: " + fileName + " → " + folderName);
                    } catch (IOException e) {
                        System.err.println("Erro ao mover " + fileName);
                    }
                }
            }
        });
    }
}
