package com.example.daniela.teachersjob;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.internal.LinkedTreeMap;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryResponse;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiscoveryFragment extends Fragment {

    private PieChart chart;
    private TextInputLayout palavraTextInputLayout;
    private TextInputEditText palavraTextInputEditText;
    private ImageView pesquisarImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        palavraTextInputLayout = getView().findViewById(R.id.palavraTextInputLayout);
        palavraTextInputEditText = getView().findViewById(R.id.palavraTextInputEditText);
        pesquisarImageView = getView().findViewById(R.id.pesquisarImageView);
        pesquisarImageView.setOnClickListener(EventPesquisar);
        inicializaChart("Análise de Sentimento", 33f, 33f, 33f);

    }

    View.OnClickListener EventPesquisar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //obtem uma lista de resultados. Só nos interessa o primeiro.
            String textoCapiturado = palavraTextInputEditText.getText().toString().trim();  //O que foi digitado
            if (TextUtils.isEmpty(textoCapiturado)) {   //Se estiver vazio
                palavraTextInputLayout.setError(getString(R.string.nada_digitado).toString());  //aparece abaixo do TextInputLayout
            }
            else{ //mandar as localizações
                //coloca o resultado obtido como texto central do gráfico.
                chart.setCenterText(textoCapiturado);
                chart.invalidate();
                //finalmente invocamos o Watson para dizer qual o sentimento geral sobre a query digitada
                new ExecutaQueryWatson().execute(textoCapiturado);
            }
        }
    };

    private void inicializaChart (String centralText, float... v){
        //busca o gráfico pelo id
        chart = (PieChart) getView().findViewById(R.id.chart);
        //constrói um ArrayList com dados para o gráfico, incialmente não há valores
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(v[0], "Positive"));
        entries.add(new PieEntry(v[1], "Negative"));
        entries.add(new PieEntry(v[2], "Neutral"));
        //desliga a legenda, opcional
        chart.getLegend().setEnabled(false);
        //tamanho do texto no centro
        chart.setCenterTextSize(20f);
        //o ArrayList é encapsulado por um PieDataSet
        PieDataSet set = new PieDataSet(entries, "");
        //Cores para cada possível valor, na ordem em que eles são definidos private float v[] = {98.8f,123.8f,161.6f};
        set.setColors(Color.BLUE,Color.RED,Color.GRAY);
        // passando os dados para o gráfico
        PieData data = new PieData(set);
        chart.setData(data);
        //conR.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark0figurações finais
        if (centralText != null)
            chart.setCenterText(centralText);
        chart.setCenterTextTypeface(Typeface.SANS_SERIF);
        //redesenha o gráfico
        chart.invalidate();
    }

    private class ExecutaQueryWatson extends AsyncTask<String, Void, float[]> {
        @Override
        protected float[] doInBackground(String... query) {

            float results[] = new float [3];

            try{
                String userName = "0764f405-a870-4435-9165-1c4268c9f554";
                String password = "2mR1muxwvRtn";
                String collectionID = "news-en";
                String environmentID = "system";

                Discovery discovery = new Discovery("2017-11-07");
                discovery.setEndPoint("https://gateway.watsonplatform.net/discovery/api");
                discovery.setUsernameAndPassword(userName, password);

                QueryRequest.Builder queryBuilder = new QueryRequest.Builder(environmentID, collectionID);
                queryBuilder.query("enriched_text.entities.text:" + query[0]);

                QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute();

                for (Map<String, Object> item : queryResponse.getResults()) {
                    pegarSentimentos(item, results);
                }
            }
            catch (final Exception e){
                //se der exceção o gráfico ficará com zero em todas as partes
                return new float [3];
            }

            return results;
        }

        @Override
        protected void onPostExecute(float[] results) {
            //redesenha o gráfico, null pq o texto já foi configurado quando a fala foi capturada
            String teste1 = "" + results[0] + " " + results[1] + " " + results[2];
            Toast.makeText(getContext(), teste1,
                    Toast.LENGTH_LONG).show();
            inicializaChart (null, results);
        }
    }

    private void pegarSentimentos(Map<String, Object> item, float[] results) {

        Map<String, LinkedTreeMap<String,Object>> mapEnrichedTitle = (Map<String, LinkedTreeMap<String, Object>>) item.get("enriched_title");
        LinkedTreeMap<String, Object> mapSentiment = mapEnrichedTitle.get("sentiment");
        Map<String, String> mapDocument = (Map<String, String>) mapSentiment.get("document");

        String r = (String) mapDocument.get("label");
        if(r.equalsIgnoreCase("positive")){
            results[0]++;
        }else if(r.equalsIgnoreCase("negative")){
            results[1]++;
        }else{
            results[2]++;
        }

    }
}
