package myApp.form;

import myApp.entity.PersonsEntity;

public class UsersMoney {
    private PersonsEntity personsEntity;
    private int money;

    public UsersMoney(PersonsEntity personsEntity, int money) {
        this.personsEntity = personsEntity;
        this.money = money;
    }

    public PersonsEntity getPersonsEntity() {
        return personsEntity;
    }

    public void setPersonsEntity(PersonsEntity personsEntity) {
        this.personsEntity = personsEntity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
