package com.example.pigeon.service.impl;

import com.example.pigeon.dto.PigeonDto;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.repository.PigeonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PigeonServiceImplTest {

    @Mock
    private PigeonRepository pigeonRepository;

    @InjectMocks
    private PigeonServiceImpl pigeonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void addPigeon_ShouldReturnPigeonDto_WhenValidData() {
//        PigeonDto pigeonDto = new PigeonDto("m123", "red", 2, "eleveurId1");
//        Pigeon pigeon = pigeonDto.toEntity();
//        when(pigeonRepository.save(any(Pigeon.class))).thenReturn(pigeon);
//
//        PigeonDto result = pigeonService.addPigeon(pigeonDto);
//
//        assertNotNull(result);
//        assertEquals(pigeonDto.getNumeroBague(), result.getNumeroBague());
//        assertEquals(pigeonDto.getCouleur(), result.getCouleur());
//        assertEquals(pigeonDto.getAge(), result.getAge());
//        assertEquals(pigeonDto.getEleveurId(), result.getEleveurId());
//        verify(pigeonRepository, times(1)).save(any(Pigeon.class));
//    }

    @Test
    void addPigeon_ShouldThrowException_WhenAgeIsInvalid() {

        PigeonDto pigeonDto = new PigeonDto("m123", "red", -1, "eleveurId1");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pigeonService.addPigeon(pigeonDto);
        });
        assertEquals("L'âge doit être supérieur à 0", exception.getMessage());
    }

    @Test
    void getAllPigeons_ShouldReturnListOfPigeons() {
        Pigeon pigeon1 = new Pigeon("m123", "red", 2, "eleveurId1");
        Pigeon pigeon2 = new Pigeon("f456", "blue", 3, "eleveurId2");
        List<Pigeon> pigeons = Arrays.asList(pigeon1, pigeon2);
        when(pigeonRepository.findAll()).thenReturn(pigeons);

        List<Pigeon> result = pigeonService.getAllPigeons();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("m123", result.get(0).getNumeroBague());
        assertEquals("f456", result.get(1).getNumeroBague());
        verify(pigeonRepository, times(1)).findAll();
    }

    @Test
    void getPigeonsByUserId_ShouldReturnPigeons_WhenUserIdExists() {
        String eleveurId = "eleveurId1";
        Pigeon pigeon1 = new Pigeon("m123", "red", 2, eleveurId);
        Pigeon pigeon2 = new Pigeon("f456", "blue", 3, eleveurId);
        List<Pigeon> pigeons = Arrays.asList(pigeon1, pigeon2);
        when(pigeonRepository.findByEleveurId(eleveurId)).thenReturn(pigeons);

        List<Pigeon> result = pigeonService.getPigeonsByUserId(eleveurId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(eleveurId, result.get(0).getEleveurId());
        verify(pigeonRepository, times(1)).findByEleveurId(eleveurId);
    }

    @Test
    void getPigeonsByUserId_ShouldReturnEmptyList_WhenUserIdDoesNotExist() {
        String eleveurId = "nonExistentId";
        when(pigeonRepository.findByEleveurId(eleveurId)).thenReturn(Arrays.asList());

        List<Pigeon> result = pigeonService.getPigeonsByUserId(eleveurId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pigeonRepository, times(1)).findByEleveurId(eleveurId);
    }

    @Test
    void getPigeonsByIds_ShouldReturnPigeons_WhenIdsExist() {

        List<String> pigeonIds = Arrays.asList("m123", "f456");
        Pigeon pigeon1 = new Pigeon("m123", "red", 2, "eleveurId1");
        Pigeon pigeon2 = new Pigeon("f456", "blue", 3, "eleveurId2");
        when(pigeonRepository.findAllById(pigeonIds)).thenReturn(Arrays.asList(pigeon1, pigeon2));

        List<Pigeon> result = pigeonService.getPigeonsByIds(pigeonIds);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("m123", result.get(0).getNumeroBague());
        assertEquals("f456", result.get(1).getNumeroBague());
        verify(pigeonRepository, times(1)).findAllById(pigeonIds);
    }

    @Test
    void getPigeonsByIds_ShouldReturnEmptyList_WhenIdsDoNotExist() {

        List<String> pigeonIds = Arrays.asList("nonExistentId1", "nonExistentId2");
        when(pigeonRepository.findAllById(pigeonIds)).thenReturn(Arrays.asList());

        List<Pigeon> result = pigeonService.getPigeonsByIds(pigeonIds);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pigeonRepository, times(1)).findAllById(pigeonIds);
    }

}