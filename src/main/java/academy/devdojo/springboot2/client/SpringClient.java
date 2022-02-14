package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {


        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}",
                Anime.class, 2);
        log.info(entity);
        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}",
                Anime.class, 2);

        log.info(object);


        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
        log.info(exchange.getBody()); //retorna uma lista

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        //Enviando um anime

//        Anime kingdom = Anime.builder().name("kingdom").build();
//        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//        log.info("saved anime {}", animeSaved);

                Anime samuraiChamploo = Anime.builder().name("samuraiChamploo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST, new HttpEntity<>(samuraiChamploo), Anime.class);
        log.info("saved anime {}", samuraiChamplooSaved);


             Anime animeToBeUpdate = samuraiChamplooSaved.getBody();
             animeToBeUpdate.setName("Samurai Champloo 2");



        ResponseEntity<Void>   samuraiChamplooUpdate= new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT, new HttpEntity<>(animeToBeUpdate), Void.class);

        log.info(samuraiChamplooUpdate);
        ResponseEntity<Void> samuraiChamplooDelete = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE, new HttpEntity<>(animeToBeUpdate), Void.class, animeToBeUpdate.getId());

        log.info(samuraiChamplooDelete);

    }


}
