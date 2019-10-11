package practice.com.example.xin.app.ui.cat

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import practice.com.example.xin.app.data.breed.Breed
import practice.com.example.xin.app.data.breed.BreedDAO
import practice.com.example.xin.app.data.img.Image
import practice.com.example.xin.app.repository.ImageRepository


class CatViewModelTest {

    @Mock
    private lateinit var breedDAO: BreedDAO

    @Mock
    private lateinit var repo: ImageRepository


    @InjectMocks
    lateinit var catViewModel: CatViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        catViewModel = CatViewModel(breedDAO, repo)
    }

    @Test
    fun testLoadImage() {
        val images: ArrayList<Image> = ArrayList()
        images.add(Image(url = "url1"))
        images.add(Image(url = "url2"))
        Mockito.`when`(repo.getImageForCat(anyString()))
            .thenReturn(Observable.just(images))
        catViewModel.loadImage().test().assertComplete()
            .assertNoErrors()
            .assertValue("url1")

    }

    @Test
    fun testInitBreed() {
        val idTest = "jjjj"
        val breedTest = Breed(
            id = idTest,
            name = "JerJerCat",
            description = "cute",
            origin = "France",
            temperament = "cool",
            hypoallergenic = 0
        )
        Mockito.`when`(breedDAO.getBreed(anyString())).thenReturn(breedTest)

        catViewModel.initBreed(id = idTest)

        Assert.assertEquals(idTest, catViewModel.breedLiveData.value?.id)
        Assert.assertEquals("JerJerCat", catViewModel.breedLiveData.value?.name)
        Assert.assertEquals("cute", catViewModel.breedLiveData.value?.description)
        Assert.assertEquals("France", catViewModel.breedLiveData.value?.origin)
        Assert.assertEquals("cool", catViewModel.breedLiveData.value?.temperament)
        Assert.assertEquals(0, catViewModel.breedLiveData.value?.hypoallergenic)
    }


}
