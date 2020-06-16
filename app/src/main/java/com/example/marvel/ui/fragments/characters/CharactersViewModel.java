package com.example.marvel.ui.fragments.characters;

import androidx.lifecycle.MutableLiveData;

import com.example.marvel.api.model.Character;
import com.example.marvel.api.model.SectionCharacters;
import com.example.marvel.api.response.CharactersResponse;
import com.example.marvel.ui.base.BaseViewModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharactersViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<SectionCharacters>> sectionCharacters;
    private ArrayList<Character> heroes;
    private ArrayList<Character> villain;
    private ArrayList<Character> antiHeroes;
    private ArrayList<Character> aliens;
    private ArrayList<Character> humans;
    private ArrayList<SectionCharacters> sectionCharactersArrayList;
    private boolean isHeroes, isVillain, isAntiHeroes, isAliens, isHumans;
    private SectionCharacters sectionListHeroes, sectionListVillain, sectionListAntiHero, sectionListAliens, sectionListHumans;

    public MutableLiveData<ArrayList<SectionCharacters>> getSectionCharacters() {
        if (sectionCharacters == null) {
            sectionCharacters = new MutableLiveData<>();

            getCharacter();
        }

        return sectionCharacters;
    }

    private void getCharacter() {
        this.beforeRequest();

        disposable.add(
                apiRepository.getAllCharacters()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<CharactersResponse>() {

                            @Override
                            public void onSuccess(CharactersResponse charactersResponse) {
                                afterRequest();
                                sectionCharactersArrayList = new ArrayList<>();

                                sectionListHeroes = new SectionCharacters();
                                sectionListHeroes.setHeaderTitle("Heróis");

                                if (charactersResponse.getHeroes().size() > 0) {
                                    heroes = new ArrayList<>();
                                    for (int i = 0; i < charactersResponse.getHeroes().size(); i++) {
                                        heroes.add(new Character(charactersResponse.getHeroes().get(i).getName(),
                                                charactersResponse.getHeroes().get(i).getAlterEgo(),
                                                charactersResponse.getHeroes().get(i).getImagePath(),
                                                charactersResponse.getHeroes().get(i).getBiography(),
                                                charactersResponse.getHeroes().get(i).getCaracteristics(),
                                                charactersResponse.getHeroes().get(i).getAbilities(),
                                                charactersResponse.getHeroes().get(i).getMovies()));
                                    }
                                }

                                sectionListHeroes.setCharacters(heroes);
                                sectionCharactersArrayList.add(sectionListHeroes);

                                sectionListVillain = new SectionCharacters();
                                sectionListVillain.setHeaderTitle("Vilões");

                                if (charactersResponse.getVillains().size() > 0) {
                                    villain = new ArrayList<>();
                                    for (int i = 0; i < charactersResponse.getVillains().size(); i++) {
                                        villain.add(new Character(charactersResponse.getVillains().get(i).getName(),
                                                charactersResponse.getVillains().get(i).getAlterEgo(),
                                                charactersResponse.getVillains().get(i).getImagePath(),
                                                charactersResponse.getVillains().get(i).getBiography(),
                                                charactersResponse.getVillains().get(i).getCaracteristics(),
                                                charactersResponse.getVillains().get(i).getAbilities(),
                                                charactersResponse.getVillains().get(i).getMovies()));
                                    }
                                }

                                sectionListVillain.setCharacters(villain);
                                sectionCharactersArrayList.add(sectionListVillain);

                                sectionListAntiHero = new SectionCharacters();
                                sectionListAntiHero.setHeaderTitle("Anti-heróis");

                                if (charactersResponse.getAntiHeroes().size() > 0) {
                                    antiHeroes = new ArrayList<>();
                                    for (int i = 0; i < charactersResponse.getAntiHeroes().size(); i++) {
                                        antiHeroes.add(new Character(charactersResponse.getAntiHeroes().get(i).getName(),
                                                charactersResponse.getAntiHeroes().get(i).getAlterEgo(),
                                                charactersResponse.getAntiHeroes().get(i).getImagePath(),
                                                charactersResponse.getAntiHeroes().get(i).getBiography(),
                                                charactersResponse.getAntiHeroes().get(i).getCaracteristics(),
                                                charactersResponse.getAntiHeroes().get(i).getAbilities(),
                                                charactersResponse.getAntiHeroes().get(i).getMovies()));
                                    }
                                }

                                sectionListAntiHero.setCharacters(antiHeroes);
                                sectionCharactersArrayList.add(sectionListAntiHero);

                                sectionListAliens = new SectionCharacters();
                                sectionListAliens.setHeaderTitle("Alieníngenas");

                                if (charactersResponse.getAliens().size() > 0) {
                                    aliens = new ArrayList<>();
                                    for (int i = 0; i < charactersResponse.getAliens().size(); i++) {
                                        aliens.add(new Character(charactersResponse.getAliens().get(i).getName(),
                                                charactersResponse.getAliens().get(i).getAlterEgo(),
                                                charactersResponse.getAliens().get(i).getImagePath(),
                                                charactersResponse.getAliens().get(i).getBiography(),
                                                charactersResponse.getAliens().get(i).getCaracteristics(),
                                                charactersResponse.getAliens().get(i).getAbilities(),
                                                charactersResponse.getAliens().get(i).getMovies()));
                                    }
                                }

                                sectionListAliens.setCharacters(aliens);
                                sectionCharactersArrayList.add(sectionListAliens);

                                sectionListHumans = new SectionCharacters();
                                sectionListHumans.setHeaderTitle("Humanos");

                                if (charactersResponse.getHumans().size() > 0) {
                                    humans = new ArrayList<>();
                                    for (int i = 0; i < charactersResponse.getHumans().size(); i++) {
                                        humans.add(new Character(charactersResponse.getHumans().get(i).getName(),
                                                charactersResponse.getHumans().get(i).getAlterEgo(),
                                                charactersResponse.getHumans().get(i).getImagePath(),
                                                charactersResponse.getHumans().get(i).getBiography(),
                                                charactersResponse.getHumans().get(i).getCaracteristics(),
                                                charactersResponse.getHumans().get(i).getAbilities(),
                                                charactersResponse.getHumans().get(i).getMovies()));
                                    }
                                }

                                sectionListHumans.setCharacters(humans);
                                sectionCharactersArrayList.add(sectionListHumans);

                                sectionCharacters.postValue(sectionCharactersArrayList);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                afterRequest(e);
                            }
                        })
        );
    }

    public void isHeroes() {
        isHeroes = !isHeroes;

        isVillain = false;
        isAntiHeroes = false;
        isAliens = false;
        isHumans = false;

        getHeroes();
    }

    private void getHeroes() {
        sectionCharacters.removeObservers(lifecycleOwner);
        if (isHeroes) {
            sectionCharactersArrayList = new ArrayList<>();
            sectionCharactersArrayList.add(sectionListHeroes);

            sectionCharacters.postValue(sectionCharactersArrayList);
        } else {
            getCharacter();
        }
    }

    public void isVillains() {
        isVillain = !isVillain;

        isHeroes = false;
        isAntiHeroes = false;
        isAliens = false;
        isHumans = false;

        getVillains();
    }

    private void getVillains() {
        sectionCharacters.removeObservers(lifecycleOwner);
        if (isVillain) {
            sectionCharactersArrayList = new ArrayList<>();
            sectionCharactersArrayList.add(sectionListVillain);

            sectionCharacters.postValue(sectionCharactersArrayList);
        } else {
            getCharacter();
        }
    }

    public void isAntiHeroes() {
        isAntiHeroes = !isAntiHeroes;

        isHeroes = false;
        isVillain = false;
        isAliens = false;
        isHumans = false;

        getAntiHeroes();
    }

    private void getAntiHeroes() {
        sectionCharacters.removeObservers(lifecycleOwner);
        if (isAntiHeroes) {
            sectionCharactersArrayList = new ArrayList<>();
            sectionCharactersArrayList.add(sectionListAntiHero);

            sectionCharacters.postValue(sectionCharactersArrayList);
        } else {
            getCharacter();
        }
    }

    public void isAliens() {
        isAliens = !isAliens;

        isHeroes = false;
        isVillain = false;
        isAntiHeroes = false;
        isHumans = false;

        getIsAliens();
    }

    private void getIsAliens() {
        sectionCharacters.removeObservers(lifecycleOwner);
        if (isAliens) {
            sectionCharactersArrayList = new ArrayList<>();
            sectionCharactersArrayList.add(sectionListAliens);

            sectionCharacters.postValue(sectionCharactersArrayList);
        } else {
            getCharacter();
        }
    }

    public void isHumans() {
        isHumans = !isHumans;

        isHeroes = false;
        isVillain = false;
        isAntiHeroes = false;
        isAliens = false;

        getIsHumans();
    }

    private void getIsHumans() {
        sectionCharacters.removeObservers(lifecycleOwner);
        if (isHumans) {
            sectionCharactersArrayList = new ArrayList<>();
            sectionCharactersArrayList.add(sectionListHumans);

            sectionCharacters.postValue(sectionCharactersArrayList);
        } else {
            getCharacter();
        }
    }
}