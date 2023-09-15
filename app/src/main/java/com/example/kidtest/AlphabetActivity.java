package com.example.kidtest;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidtest.adapter.ItemAdapter;

public class AlphabetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int position;
    String[] item;
    int[] item_image;
    ItemAdapter ad;
    TextToSpeech t1 = MainActivity.t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        position = getIntent().getExtras().getInt("position");
        Log.d("pos", String.valueOf(position));
        recyclerView = findViewById(R.id.item_recycler);
        switch (position) {
            case 0:
                item = new String[]{"A for Apple", "B for Ball", "C for Cat", "D for Dog", "E for Elephant", "F for Fish", "G for Giraffe", "H for Home", "I for Ice-Cream", "J for Joker",
                        "K for kangaroo", "L for Lion", "M for Monkey", "N for Nest", "O for Owl", "P for Parrot", "Q for Queen", "R for Rabbit", "S for Sun", "T for tiger", "U for Umbrella",
                        "V for Violin", "W for Watermelon", "X for X-Mas", "Y for Yak", "Z for Zebra"
                };
                item_image = new int[]{R.drawable.alphabet_main, R.drawable.b, R.drawable.cat, R.drawable.dog, R.drawable.elephant, R.drawable.f, R.drawable.giraffe, R.drawable.h, R.drawable.i, R.drawable.j,
                        R.drawable.kangaroo, R.drawable.lion, R.drawable.m, R.drawable.n, R.drawable.owl, R.drawable.parrot, R.drawable.q, R.drawable.rabbit, R.drawable.s,
                        R.drawable.tiger, R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z

                };
                break;
            case 1:
                item = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
                item_image = new int[]{R.drawable.zero, R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven,
                        R.drawable.eight, R.drawable.nine};
                break;
            case 2:
                item = new String[]{"Tiger", "Lion", "Kangaroo", "Elephant", "Monkey", "Giraffe", "Horse", "Donkey", "Bear", "Cow", "Dog", "Cat"};
                item_image = new int[]{R.drawable.tiger, R.drawable.lion, R.drawable.kangaroo, R.drawable.elephant, R.drawable.m, R.drawable.giraffe, R.drawable.horse, R.drawable.donkey, R.drawable.bear
                        , R.drawable.cow, R.drawable.dog, R.drawable.cat
                };
                break;
            case 3:
                item = new String[]{"Crow", "Duck", "Dove", "Eagle", "Hen", "Ostrich", "Owl", "Parrot", "Peacock", "Sparrow", "Swan", "Turkey", "Woodpecker"};
                item_image = new int[]{R.drawable.crow, R.drawable.duck, R.drawable.dove, R.drawable.eagle, R.drawable.hen, R.drawable.ostrich, R.drawable.owl, R.drawable.parrot,
                        R.drawable.peacock, R.drawable.sparrow, R.drawable.swan, R.drawable.turkey, R.drawable.woodpecker
                };
                break;
            case 4:
                item = new String[]{"Sunflower", "Tulip", "Lily", "Lotus", "Rose", "Marigold", "Jasmin", "Daisy", "Orchids"};
                item_image = new int[]{R.drawable.sunflower, R.drawable.tulip, R.drawable.lily, R.drawable.lotus, R.drawable.rose, R.drawable.marigold, R.drawable.jasmine, R.drawable.daisy, R.drawable.orchids
                };
                break;
            case 5:
                item = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                item_image = new int[]{R.drawable.january, R.drawable.february, R.drawable.march, R.drawable.april, R.drawable.may, R.drawable.june, R.drawable.july
                        , R.drawable.august, R.drawable.september, R.drawable.october, R.drawable.november, R.drawable.december};
                break;
            case 6:
                item = new String[]{"Black", "Blue", "Brown", "Green", "Red", "Yellow", "White", "Orange", "Pink", "Purple"};
                item_image = new int[]{R.drawable.black, R.drawable.blue, R.drawable.brown, R.drawable.green, R.drawable.red, R.drawable.yellow, R.drawable.white, R.drawable.orange, R.drawable.pink, R.drawable.purple};
                break;
            case 7:
                item = new String[]{"Beet", "Brinjal", "Cabbage", "Carrot", "Cauliflower", "Red-chili", "Corn", "Cucumber", "Garlic", "Mushroom", "Onion", "Potato", "Pumpkin", "Tomato"};
                item_image = new int[]{R.drawable.beet, R.drawable.brinjal, R.drawable.cabbage, R.drawable.carrot, R.drawable.cauliflower, R.drawable.redchili, R.drawable.corn, R.drawable.cucumber, R.drawable.garlic, R.drawable.mushroom, R.drawable.onion, R.drawable.potato
                        , R.drawable.pumpkin, R.drawable.tomato
                };
                break;
            case 8:
                item = new String[]{"Earth", "Jupiter", "Mars", "Saturn", "Uranus", "Pluto", "Neptune", "Venus", "Mercury"};
                item_image = new int[]{R.drawable.earth, R.drawable.jupitar, R.drawable.mars, R.drawable.saturn, R.drawable.uranus, R.drawable.pluto, R.drawable.neptune, R.drawable.venus, R.drawable.mercury};
                break;
            case 9:
                item = new String[]{"Airplane ", "Bicycle", "Boat", "Bus", "Car", "Motorcycle", " School-bus", " Ship", "Truck"};
                item_image = new int[]{R.drawable.airplane, R.drawable.bicycle, R.drawable.boat, R.drawable.bus, R.drawable.car, R.drawable.motorcycle, R.drawable.schoolbus, R.drawable.ship, R.drawable.truck};
                break;
            case 10:
                item = new String[]{"Ear", "Eye", "Eyebrow", "Foot", "Hand", "Leg", "Lips", "Nose", "Teeth", "Tongue"};
                item_image = new int[]{R.drawable.ear, R.drawable.eye, R.drawable.eyebrow, R.drawable.foot, R.drawable.hand, R.drawable.leg, R.drawable.lips, R.drawable.nose, R.drawable.teeth, R.drawable.tongue};
                break;
        }
        if (item != null) {
            ad = new ItemAdapter(this, item, t1, item_image);
            recyclerView.setAdapter(ad);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}