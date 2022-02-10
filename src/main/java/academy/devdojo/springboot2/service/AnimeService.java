package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.request.AnimePostRequestBody;
import academy.devdojo.springboot2.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> findByName(String name) {

        return animeRepository.findByName(name);
    }

    public List<Anime> listAll() {

        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id) {

        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));

    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));

    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        animeRepository.save(anime);
    }
}
