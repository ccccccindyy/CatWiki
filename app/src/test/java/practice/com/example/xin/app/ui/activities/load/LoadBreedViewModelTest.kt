package practice.com.example.xin.app.ui.activities.load

import com.nhaarman.mockitokotlin2.any
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.repository.BreedRepository

class LoadBreedViewModelTest {

    @Mock
    private lateinit var breedDAO: BreedDAO

    @Mock
    private lateinit var repo: BreedRepository


    @InjectMocks
    lateinit var loadBreedViewModel: LoadBreedViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loadBreedViewModel = LoadBreedViewModel(breedDAO, repo)
    }

    @Test
    fun loadCatBreedsTest() {

        val breed1 = Breed(
            id = "jjjj",
            name = "JerJerCat",
            description = "cute",
            origin = "France",
            temperament = "cool",
            hypoallergenic = 0
        )
        val breed2 = Breed(
            id = "xxxx",
            name = "XinXinCat",
            description = "cute",
            origin = "China",
            temperament = "bouncy",
            hypoallergenic = 0
        )
        val bList: ArrayList<Breed> = ArrayList()
        bList.add(breed1)
        bList.add(breed2)

        Mockito.`when`(repo.getBreeds()).thenReturn(Observable.just(bList))
        Mockito.`when`(breedDAO.addBreed(any())).thenReturn(true)

        loadBreedViewModel.loadCatBreeds().test().assertComplete().assertNoErrors()


    }
}