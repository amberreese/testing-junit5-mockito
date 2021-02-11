package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        //given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        //then
        then(specialityRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findIdTest() {
        //given
        Speciality speciality = new Speciality();
        given(specialityRepository.findById(1l)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(specialityRepository).isNotNull();
        then(specialityRepository).should(times(1)).findById(anyLong());
        then(specialityRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        // given - none

        // when
        service.deleteById(1l);

        //then
        then(specialityRepository).should(times(1)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        // give - none

        // when
        service.deleteById(1l);

        //then
        then(specialityRepository).should(atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        // when
        service.deleteById(1l);

        // then
        then(specialityRepository).should(atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        // then
        then(specialityRepository).should(atLeastOnce()).deleteById(1l);
        then(specialityRepository).should(never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        // when
        service.delete(new Speciality());

        //then
        then(specialityRepository).should().delete(any());
    }
}