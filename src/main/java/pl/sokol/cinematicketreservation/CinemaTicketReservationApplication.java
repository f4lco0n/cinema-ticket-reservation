package pl.sokol.cinematicketreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sokol.cinematicketreservation.DAO.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CinemaTicketReservationApplication implements CommandLineRunner {

	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRolesRepo userRolesRepo;

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketReservationApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init(){

		UserRoles adminRoles = new UserRoles("ADMIN");
		UserRoles usersRoles = new UserRoles("USER");

		User adminUser = new User("admin",passwordEncoder.encode("admin"));
		User defaultUser = new User("user",passwordEncoder.encode("user"));

		Set<User> adminUsersSet = new HashSet<>();
		adminUsersSet.add(adminUser);

		adminRoles.setUserSet((Set) adminUsersSet);

		Set<User> defaultUsersSet = new HashSet<>();
		defaultUsersSet.add(defaultUser);
		usersRoles.setUserSet((Set) defaultUsersSet);

		Set<UserRoles> adminRolesSet = new HashSet<>();
		adminRolesSet.add(adminRoles);
		adminUser.setUsersRolesSet(adminRolesSet);

		Set<UserRoles> defaultUserRolesSet = new HashSet<>();
		defaultUserRolesSet.add(usersRoles);
		defaultUser.setUsersRolesSet(defaultUserRolesSet);

		userRepo.save(adminUser);
		userRepo.save(defaultUser);

		userRolesRepo.save(adminRoles);
		userRolesRepo.save(usersRoles);



	}

	@Override
	public void run(String... args) throws Exception {
		savaData();
	}

	@Transactional
	public void savaData() {
		Movie zielona_mila = new Movie("Zielona Mila","Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie, którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.");
		Movie skazani = new Movie("Skazani na Shawshank", "Adaptacja opowiadania Stephena Kinga. Niesłusznie skazany na dożywocie bankier, stara się przetrwać w brutalnym, więziennym świecie.");

		movieRepo.saveAll(Arrays.asList(zielona_mila,skazani));
	}
}

