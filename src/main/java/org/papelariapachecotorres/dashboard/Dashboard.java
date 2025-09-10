package org.papelariapachecotorres.dashboard;

public class Dashboard {
    private int totalClientes;
    private int totalProdutos;
    private int totalEstoque;
    private int vendasHoje;

    public Dashboard(int totalClientes, int totalProdutos, int totalEstoque, int vendasHoje) {
        this.totalClientes = totalClientes;
    }

    public int getTotalClientes() {
        return totalClientes;
    }

    public int getTotalProdutos() {
        return totalProdutos;
    }

    public int getTotalEstoque() {
        return totalEstoque;
    }

    public int getVendasHoje() {
        return vendasHoje;
    }
}