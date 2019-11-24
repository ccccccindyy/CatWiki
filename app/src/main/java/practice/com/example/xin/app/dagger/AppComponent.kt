package practice.com.example.xin.app.dagger

import dagger.BindsInstance
import dagger.Component
import practice.com.example.xin.app.Application
import practice.com.example.xin.app.ui.activities.load.LoadBreedActivity
import practice.com.example.xin.app.ui.breed.BreedListFragment
import practice.com.example.xin.app.ui.cat.CatFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, FirebaseModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun firebaseModule(firebaseModule: FirebaseModule): Builder

        fun build(): AppComponent
    }

    fun inject(loadBreedActivity: LoadBreedActivity)
    fun inject(application: Application)
    fun inject(breedListFragment: BreedListFragment)
    fun inject(catFragment: CatFragment)
}