package com.github.VLucas3.organizadorarquivos;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Organizador de Arquivos ===");
        System.out.println("Criado por VLucas3 (github.com/VLucas3)");
        System.out.print("Digite o caminho da pasta (ex: C:/Users/SeuNome/Downloads): ");
        String path = scanner.nextLine();
        
        try {
            FileOrganizer.organizeFiles(path);
            System.out.println("Pronto! Arquivos organizados.");
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
