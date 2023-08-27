package br.com.fiap;

import br.com.fiap.domain.entity.Advogado;
import br.com.fiap.domain.entity.Estado;
import br.com.fiap.domain.entity.Processo;
import br.com.fiap.domain.entity.TipoDeAcao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

        EntityManager manager = factory.createEntityManager();


        //salvarProcesso(manager);

        buscarProcesso(manager);

        //listarProcessos(manager);

        manager.close();
        factory.close();


    }

    private static void listarProcessos(EntityManager manager) {
        List<Processo> processos = manager.createQuery("FROM Processo").getResultList();

        processos.forEach(System.out::println);
    }

    private static void buscarProcesso(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("ID do processo: "));
        Processo processo = manager.find(Processo.class, id);
        System.out.println(processo);
    }

    private static void salvarProcesso(EntityManager manager) {

        TipoDeAcao tipoAcao = new TipoDeAcao(null, "trabalhista");
        Estado estado = new Estado(null, "Salvador", "BA");
        Advogado advogado = new Advogado(null,"Ana", "654263", estado);
        Processo processo = new Processo(null, "67891", true, advogado, tipoAcao);

        manager.getTransaction().begin();
        manager.persist(processo);
        manager.getTransaction().commit();
    }
}