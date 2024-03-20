package Reservation.Reservation_airplain_demo.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Reservation.Reservation_airplain_demo.models.Utilisateur;
import jakarta.transaction.Transactional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur ,Long> {

    @Query(value = " Select * FROM utilisateur where NomUtilisateur=?1 and MotDePasse=?2", nativeQuery = true)
    Utilisateur login(String NomUtilisateur, String MotDePasse);

    @Query(value = " Select * FROM utilisateur where NomUtilisateur=?1", nativeQuery = true)
    Utilisateur findByNomUtilisateur(String nomUtilisateur);
}
