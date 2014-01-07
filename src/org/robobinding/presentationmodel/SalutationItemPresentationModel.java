package org.robobinding.presentationmodel;

public class SalutationItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<Salutation> {

    private Salutation salutation;

    public String getSalutation(){
        return salutation.toString();
    }

    @Override
    public void updateData(int index, Salutation salutation) {
        this.salutation = salutation;
    }

}
