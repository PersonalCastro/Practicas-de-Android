package com.example.myapplicationcalculator.ui.estandar;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplicationcalculator.R;

public class EstandarFragment extends Fragment {

    private EstandarViewModel estandarViewModel;

    public Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAC, buttonBackspace, buttonPercentaje, buttonDouble, buttonDivision, buttonMultiplication, buttonSubtraction, buttonAddition, buttonEqual ;
    public TextView actualButton, historico/*, debug*/;

    public static boolean suma, resta, multiplicacion, division;

    public static String auxNumeroActual, historialAntiguoNumero;

    public static float resultadoFinal, resultadofinal2;

    public static String nextOperacion;

    public static boolean porcentaje;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        estandarViewModel =
                ViewModelProviders.of(this).get(EstandarViewModel.class);

        View root = inflater.inflate(R.layout.fragment_estandar, container, false);



        //Declaramos el textView
        actualButton = (TextView) root.findViewById(R.id.textViewAcualButton);
        historico = (TextView) root.findViewById(R.id.historical);
        //debug = (TextView) root.findViewById(R.id.textViewResult);
        auxNumeroActual = new String();
        auxNumeroActual = "0";
        historialAntiguoNumero = new String();
        historialAntiguoNumero = "0";
        resultadoFinal = 0;
        porcentaje = false;
        //BOTONES

        //Declaramos los botones y sus funcionalidades

        button0 = (Button) root.findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button0.startAnimation(buttonclicked);

                if(!auxNumeroActual.equals("0")){
                    auxNumeroActual += "0";
                    resultadofinal2 = Float.valueOf(auxNumeroActual);
                }else if(porcentaje){
                    //auxNumeroActual += "0";
                    //resultadofinal2 = Float.valueOf(auxNumeroActual);
                }

                actualButton.setText(auxNumeroActual);
            }
        }) ;

        button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button1.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "1";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "1";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }


            }

        }) ;

        button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button2.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "2";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "2";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button3.startAnimation(buttonclicked);
                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "3";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "3";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button4.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "4";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "4";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button5 = (Button) root.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button5.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "5";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "5";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button6 = (Button) root.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button6.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "6";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "6";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button7 = (Button) root.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button7.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "7";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "7";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button8 = (Button) root.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button8.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "8";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "8";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }

                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        button9 = (Button) root.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                button9.startAnimation(buttonclicked);

                if(auxNumeroActual.length() < 16){
                    if(auxNumeroActual.equals("0")){
                        auxNumeroActual = "9";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }else{
                        auxNumeroActual += "9";
                        resultadofinal2 = Float.valueOf(auxNumeroActual);
                    }
                    actualButton.setText(auxNumeroActual);
                }

            }
        }) ;

        buttonAC = (Button) root.findViewById(R.id.buttonAc);
        buttonAC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(40);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonAC.startAnimation(buttonclicked);
                historico.setText("");
                Log.i("-----------------", String.valueOf(resultadoFinal));


                actualButton.setText("0");
                resultadofinal2 = 0;
                resultadoFinal = 0;
                historialAntiguoNumero = "0";
                auxNumeroActual = "0";

                porcentaje = false;

                resta = false;
                division = false;
                multiplicacion = false;
                suma = false;

            }
        }) ;

        buttonBackspace = (Button) root.findViewById(R.id.buttonBackSpace);
        buttonBackspace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonBackspace.startAnimation(buttonclicked);


                if (actualButton.getText() == "÷" || actualButton.getText() == "×" || actualButton.getText() == "-" || actualButton.getText() == "+"){

                    if(String.valueOf(historico.getText()).length() <= 5){
                        historico.setText("");

                    }else{
                        historico.setText( historico.getText().subSequence(0, historico.getText().length() -5) );
                    }

                    nextOperacion = "";
                    actualButton.setText(historialAntiguoNumero);

                    auxNumeroActual = historialAntiguoNumero;
                    Log.i("1 n_actual (*): ", String.valueOf(auxNumeroActual));

                }else{
                    Log.i("2 n_actual (*): ", String.valueOf(auxNumeroActual) + " - " + String.valueOf(auxNumeroActual).length());

                    if(auxNumeroActual.length() != 1){
                        auxNumeroActual = auxNumeroActual.substring(0,auxNumeroActual.length() -1);
                    }else if (auxNumeroActual.length() == 1){
                        auxNumeroActual = "0";
                    }else{

                    }
                    actualButton.setText(auxNumeroActual);
                }



            }
        }) ;

        buttonPercentaje = (Button) root.findViewById(R.id.buttonPorcentaje);
        buttonPercentaje.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonPercentaje.startAnimation(buttonclicked);

                float newNumero = 0;

                if(historico.getText().length() == 0 && actualButton.getText() != "0" && actualButton.getText() != "0.0"){

                    newNumero = Float.valueOf(auxNumeroActual) / 100;
                    actualButton.setText(String.valueOf(newNumero));
                    compruebaDecimal();

                }else if ( actualButton.getText() != "0" && actualButton.getText() != "0.0"){
                    newNumero = ( resultadoFinal * Float.valueOf(auxNumeroActual) ) / 100;


                    if ((int)newNumero < newNumero){
                        actualButton.setText(String.valueOf(newNumero));
                    }else{
                        actualButton.setText(String.valueOf((int) newNumero));

                    }
                    compruebaDecimal();

                    auxNumeroActual = String.valueOf(newNumero);
                }




            }
        }) ;

        buttonDivision = (Button) root.findViewById(R.id.buttonDividir);
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonDivision.startAnimation(buttonclicked);
                Log.i("1 Resultado Final (*): ", String.valueOf(resultadoFinal));


                historialAntiguoNumero = auxNumeroActual;

                //Cuentas
                if(historico.length() == 0){
                    resultadoFinal = Float.valueOf(auxNumeroActual);
                    auxNumeroActual = "0";

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){
                    hacerOperacion();
                    auxNumeroActual = "0";
                }

                nextOperacion = "÷";

                if(historico.length() == 0){
                    //resultadoFinal = Float.valueOf(auxNumeroActual);
                    historico.append(actualButton.getText() + " ÷ " );

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){

                    historico.append(actualButton.getText() + " ÷ ");
                }else{
                    historico.setText( historico.getText().subSequence(0, historico.getText().length() -3) );
                    historico.append( " ÷ " );
                }




                compruebaDecimal();


                suma = false;
                resta = false;
                multiplicacion = false;
                division = true;
                actualButton.setText("÷");
                Log.i("2 Resultado Final (*): ", String.valueOf(resultadoFinal));

            }
        }) ;

        buttonMultiplication = (Button) root.findViewById(R.id.buttonMultiplicar);
        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonMultiplication.startAnimation(buttonclicked);
                Log.i("1 Resultado Final (*): ", String.valueOf(resultadoFinal));


                historialAntiguoNumero = auxNumeroActual;

                //Cuentas
                if(historico.length() == 0){
                    resultadoFinal = Float.valueOf(auxNumeroActual);
                    auxNumeroActual = "0";

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){
                    hacerOperacion();
                    auxNumeroActual = "0";
                }

                nextOperacion = "×";

                if(historico.length() == 0){
                   // resultadoFinal = Float.valueOf(auxNumeroActual);
                    historico.append(actualButton.getText() + " × " );

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){

                    historico.append(actualButton.getText() + " × ");
                }else{
                    historico.setText( historico.getText().subSequence(0, historico.getText().length() -3) );
                    historico.append( " × " );
                }

                compruebaDecimal();


                suma = false;
                resta = false;
                division = false;
                multiplicacion = true;
                actualButton.setText("×");
                Log.i("2 Resultado Final (*): ", String.valueOf(resultadoFinal));


            }
        }) ;

        buttonSubtraction = (Button) root.findViewById(R.id.buttonRestar);
        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonSubtraction.startAnimation(buttonclicked);
                Log.i("1 Resultado Final (-): ", String.valueOf(resultadoFinal));


                historialAntiguoNumero = auxNumeroActual;


                //Cuentas
                if(historico.length() == 0){
                    resultadoFinal = Float.valueOf(auxNumeroActual);
                    auxNumeroActual = "0";

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){
                    hacerOperacion();
                    auxNumeroActual = "0";
                }

                nextOperacion = "-";


                if(historico.length() == 0){
                    //resultadoFinal = Float.valueOf(auxNumeroActual);
                    historico.append(actualButton.getText() + " - " );

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){

                    historico.append(actualButton.getText() + " - ");
                }else{
                    historico.setText( historico.getText().subSequence(0, historico.getText().length() -3) );
                    historico.append( " - " );
                }


                compruebaDecimal();

                suma = false;
                division = false;
                multiplicacion = false;
                resta = true;
                actualButton.setText("-");
                Log.i("2 Resultado Final (-): ", String.valueOf(resultadoFinal));


            }
        }) ;

        buttonAddition = (Button) root.findViewById(R.id.buttonSumar);
        buttonAddition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonAddition.startAnimation(buttonclicked);
                Log.i("1 Resultado Final (+): ", String.valueOf(resultadoFinal));

                historialAntiguoNumero = auxNumeroActual;

                //Cuentas
                if(historico.length() == 0){
                    resultadoFinal = Float.valueOf(auxNumeroActual);
                    auxNumeroActual = "0";
                    //debug.setText(String.valueOf(resultadoFinal) + "hola");

                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){
                    hacerOperacion();
                    auxNumeroActual = "0";
                }

                nextOperacion = "+";

                if(historico.length() == 0){
                    //resultadoFinal = Float.valueOf(auxNumeroActual);
                    historico.append(actualButton.getText() + " + " );
                }else if (actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){

                    historico.append(actualButton.getText() + " + ");
                }else{
                    historico.setText( String.valueOf(historico.getText()).substring(0, historico.getText().length() -3) );
                    historico.append( " + " );
                }

                compruebaDecimal();

                resta = false;
                division = false;
                multiplicacion = false;
                suma = true;
                actualButton.setText("+");
                Log.i("2 Resultado Final (+): ", String.valueOf(resultadoFinal));


            }
        }) ;

        buttonEqual = (Button) root.findViewById(R.id.buttonIgual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(40);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonEqual.startAnimation(buttonclicked);

                if(actualButton.getText() != "÷" && actualButton.getText() != "×" && actualButton.getText() != "-" && actualButton.getText() != "+"){
                    hacerOperacion();
                }


                if ((int)resultadoFinal < resultadoFinal){
                    actualButton.setText(String.valueOf(resultadoFinal));
                }else{
                    actualButton.setText(String.valueOf((int) resultadoFinal));

                }

                historico.setText("");


                resultadoFinal = 0;
                historialAntiguoNumero = "0";
                auxNumeroActual = "0";

                nextOperacion = "";

                porcentaje = false;


                resta = false;
                division = false;
                multiplicacion = false;
                suma = false;

            }
        }) ;

        buttonDouble = (Button) root.findViewById(R.id.buttonDecimal);
        buttonDouble.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                vibe.vibrate(30);

                Animation buttonclicked = AnimationUtils.loadAnimation(getActivity(),R.anim.clickbutton_event);
                buttonDouble.startAnimation(buttonclicked);

                if(!porcentaje){
                    porcentaje = true;
                    auxNumeroActual += ".";
                    actualButton.setText(actualButton.getText() + ".");
                }else if (auxNumeroActual.indexOf(".") == auxNumeroActual.length() -1){
                    auxNumeroActual = auxNumeroActual.substring(0,auxNumeroActual.length() -1);

                    actualButton.setText( String.valueOf(actualButton.getText()).substring(0, actualButton.getText().length() -1) );
                    porcentaje = false;
                }


            }
        }) ;





        return root;
    }

    public void hacerOperacion(){

        Log.i("1 Resultado Final (O): ", String.valueOf(resultadoFinal));

        if(nextOperacion == "+"){

            resultadoFinal = resultadoFinal + Float.valueOf(auxNumeroActual);
        }else if (nextOperacion == "-"){

            resultadoFinal =   resultadoFinal - Float.valueOf(auxNumeroActual);

        }else if (nextOperacion == "÷") {

            resultadoFinal = resultadoFinal /Float.valueOf(auxNumeroActual);
        }else if (nextOperacion == "×") {

            resultadoFinal = resultadoFinal * Float.valueOf(auxNumeroActual);
        }
        //debug.setText(String.valueOf(resultadoFinal));
        Log.i("2 Resultado Final (O): ", String.valueOf(resultadoFinal));

    }

    public void compruebaDecimal(){

        if(String.valueOf(actualButton.getText()).indexOf(".") != -1){
            porcentaje = true;
        }
    }

}