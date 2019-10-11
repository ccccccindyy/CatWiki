package practice.com.example.xin.app.ui.breed

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO

class BreedListViewModelTest {

    @Mock
    private lateinit var breedDAO: BreedDAO

    @InjectMocks
    lateinit var breedListViewModel: BreedListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        breedListViewModel = BreedListViewModel(breedDAO)
    }

    @Test
    fun getBreedsTest() {
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

        Mockito.`when`(breedDAO.getBreeds()).thenReturn(bList)

        val resultList = breedListViewModel.getBreeds()

        Assert.assertTrue(resultList.containsAll(bList))
    }
}