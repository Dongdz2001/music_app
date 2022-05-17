package com.dmd.music_play;

import java.util.ArrayList;
import java.util.List;

public class MainData {
    private List<IdElement> list = new ArrayList<>();
    public MainData(){

    }
    public int getImgElement(int i){
        return list.get(i).getImg();
    }
    public  int getSoundElement(int i){
        return list.get(i).getSound();
    }
    private void add(int a , int b){
        try {
            list.add(new IdElement(a,b));
        }catch (Exception e){

        }
    }
    public int size(){
        return list.size();
    }
    public void readData(){
        add(R.drawable.nguoithu33,R.raw.nguoithu33);
        add(R.drawable.talacuanhau1,R.raw.talacuanhau1);
        add(R.drawable.codondanhchoai2,R.raw.codondanhchoaiday2);
        add(R.drawable.thatinh4,R.raw.thattinh4);
        add(R.drawable.khovenucuoi5,R.raw.khovenucuoi5);
        add(R.drawable.nabgamxadan6,R.raw.nangamxadan6);
        add(R.drawable.sainguoisaithoidiem7,R.raw.sainguoisaithoidiem7);
        add(R.drawable.dungnguoidungthoidiem8,R.raw.dungnguoidungthoidiem8);
        add(R.drawable.amthambenem9,R.raw.amthambenem9);
        add(R.drawable.suynghitronganh10,R.raw.suynghitronganh10);
        add(R.drawable.hanhphucdongianlam11,R.raw.hanhphucdongianlam11);
        add(R.drawable.omemlancuoi12,R.raw.omemlancuoi12);
        add(R.drawable.danhmatem13,R.raw.danhmatem13);
        add(R.drawable.hoyeuaimatroi14,R.raw.hoyeuaimatroi14);
        add(R.drawable.ylcimg,R.raw.yeulacuiremix);
        add(R.drawable.keobonggon,R.raw.keobonggon);
        add(R.drawable.phodalenden,R.raw.phodalenden);
        add(R.drawable.roitoiluon,R.raw.roitoiluon);
        add(R.drawable.henemkiepsau,R.raw.henemkiepsau);
        add(R.drawable.tellurmom,R.raw.tellurmom);
        add(R.drawable.khuemoclang,R.raw.khuemoclang);
        add(R.drawable.thiendabg,R.raw.thiendang);
        add(R.drawable.aloha,R.raw.aloha);
        add(R.drawable.emmimcuoithatdep,R.raw.emmimcuoithatdep);
        add(R.drawable.duongquyentinhyeu,R.raw.duongquyentinhyeuz);
        add(R.drawable.namquocsonha,R.raw.namquocsonha);
        add(R.drawable.rangkhon,R.raw.raangkhon);
    }
}
