package com.example.demo.rest.responsewithstatusandheaders.model;

/* При использовании объекта в качестве модели данных, передаваемых между двумя приложениями,
 его принято называть DTO (data transfer object — объект передачи данных) */
public class Country {

    private String name;
    private int population;

    /* Чтобы было проще, мы определили для экземпляра Country статический метод генерации объекта,
     который принимает название страны и количество жителей и возвращает экземпляр Country
      с указанными значениями */
    public static Country of(String name, int population) {
        Country country = new Country();
        country.setName(name);
        country.setPopulation(population);
        return country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
