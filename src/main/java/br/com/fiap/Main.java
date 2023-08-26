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

        // Processo p1 = saveProcesso( manager );

        //  findById( manager );

        //  findAll( manager );

        manager.close();
        factory.close();


    }

    private static void findAll(EntityManager manager) {
        List<Processo> list = manager.createQuery( "FROM Processo" ).getResultList();

        list.forEach( System.out::println );
    }

    private static void findById(EntityManager manager) {
        Long id = Long.valueOf( JOptionPane.showInputDialog( "ID do Processo" ) );
        Processo processo = manager.find( Processo.class, id );
        System.out.println( processo );
    }

    private static Processo saveProcesso(EntityManager manager) {
        TipoDeAcao tp = new TipoDeAcao();
        tp.setId( null ).setNome( "DESPEJO" );

        Estado uf = new Estado( null, "Rio de Janeiro", "RJ" );

        Advogado benezinho = new Advogado();
        benezinho.setId( null )
                .setEstado( uf )
                .setNumeroOAB( "123135" )
                .setNome( "Benefrancis do Nascimento" );

        Processo p1 = new Processo();
        p1.setId( null )
                .setNumero( "1231.12.2023.6100-00" )
                .setAdvogado( benezinho )
                .setTipoDeAcao( tp )
                .setProBono( true );


        manager.getTransaction().begin();

        manager.persist( p1 );

        manager.getTransaction().commit();
        return p1;
    }
}