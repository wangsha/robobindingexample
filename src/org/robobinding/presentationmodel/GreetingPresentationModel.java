package org.robobinding.presentationmodel;

import android.text.TextUtils;
import org.robobinding.viewattribute.adapterview.ItemClickEvent;

import java.util.List;

@PresentationModel
public class GreetingPresentationModel {
    private Sex sex = Sex.Undecided;
    private Salutation salutation;
    private String firstname;
    private String lastname;

    //private boolean salutationsSpinnerEnabled;

    public void maleSelected() {
        setSex(Sex.Male);
    }
    public void femaleSelected() {
        setSex(Sex.Female);

    }

    public void setSex(Sex sex) {
        this.sex = sex;
        //setSalutationsSpinnerEnabled(sex != Sex.Undecided);
    }
          /*
    public void setSalutationsSpinnerEnabled(boolean salutationsSpinnerEnabled) {
        this.salutationsSpinnerEnabled = salutationsSpinnerEnabled;
    }

    public boolean isSalutationsSpinnerEnabled() {
        return salutationsSpinnerEnabled;
    }       */

    @DependsOnStateOf("sex")
    public boolean isSalutationsSpinnerEnabled() {
        return sex != Sex.Undecided;
    }

    @DependsOnStateOf("sex")
    public boolean isFirstnameInputEnabled() {
        return sex != Sex.Undecided;
    }

    @DependsOnStateOf("sex")
    @ItemPresentationModel(SalutationItemPresentationModel.class)
    public List<Salutation> getSalutations() {
        return sex == Sex.Female ? Salutation.forFemales() : Salutation.forMales();
    }

    public void salutationSelected(ItemClickEvent event) {
        setSalutation(getSalutations().get(event.getPosition()));
    }

    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @DependsOnStateOf("firstname")
    public boolean isLastnameInputEnabled() {
        return !TextUtils.isEmpty(firstname);
    }

    @DependsOnStateOf({"salutation", "firstname", "lastname"})
    public String getGreeting() {
        if (TextUtils.isEmpty(lastname))
            return "Please enter all details above";
        return "Hello " + salutation + " " + firstname + " " + lastname + "!";
    }


}
