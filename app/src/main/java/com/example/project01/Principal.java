package com.example.project01;

import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.android.material.chip.Chip;
        import com.google.android.material.chip.ChipGroup;

public class Principal extends AppCompatActivity {

    private ChipGroup chipGroup;
    private Button buttonProssiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        chipGroup = findViewById(R.id.chipGroup);
        buttonProssiga = findViewById(R.id.buttonProssiga);

        buttonProssiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedStyles = getSelectedStyles();

                if (!selectedStyles.isEmpty()) {
                    Toast.makeText(Principal.this, "Estilos selecionados: " + selectedStyles, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Principal.this, "Selecione pelo menos um estilo musical.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @NonNull
    private String getSelectedStyles() {
        StringBuilder selectedStyles = new StringBuilder();

        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View child = chipGroup.getChildAt(i);

            if (child instanceof Chip) {
                Chip chip = (Chip) child;

                if (chip.isChecked()) {
                    selectedStyles.append(chip.getText()).append(", ");
                }
            }
        }

        if (selectedStyles.length() > 0) {
            selectedStyles.delete(selectedStyles.length() - 2, selectedStyles.length());
        }

        return selectedStyles.toString();
    }
}
