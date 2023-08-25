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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "oracle" );
        EntityManager manager = factory.createEntityManager();

        Processo p1 = salvarProcesso( manager );
        findById( manager );

        List<Processo> list = manager.createQuery( "SELECT p FROM Processo p" ).getResultList();

        list.forEach( System.out::println );


        manager.close();
        factory.close();


    }

    private static Processo salvarProcesso(EntityManager manager) {
        TipoDeAcao tp = new TipoDeAcao( null, "Alimentos" );

        Estado uf = new Estado( null, "São Paulo", "RJ" );

        Advogado benezinho = new Advogado( null, "Benefrancis do Nascimento", "123465", uf );

        Processo p1 = new Processo();
        p1.setId( null )
                .setAdvogado( benezinho )
                .setNumero( "1521.2023.6010.25-00" )
                .setProBono( false )
                .setTipoDeAcao( tp );

        manager.getTransaction().begin();
        manager.persist( p1 );
        manager.getTransaction().commit();
        return p1;
    }

    private static void findById(EntityManager manager) {
        Long id = Long.valueOf( JOptionPane.showInputDialog( "Informe o ID do Processo: " ) );
        Processo processo = manager.find( Processo.class, id );
        System.out.println( processo );
    }
}