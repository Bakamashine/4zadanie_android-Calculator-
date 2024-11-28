package com.example.calculyator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculyator.R;

public class MainActivity extends AppCompatActivity {

    // Объявление кнопок
    private Button
            buttonAdd,
            buttonSubtract,
            buttonDivide,
            buttonMultiply,
            buttonClean,
            buttonStepen;

    // Обновление полей для текста
    private TextView operation, result;
    
    // Объявление полей для ввода текста
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Установка макета для активности

        // Инициализация элементов интерфейса
        initializeViews();

        // Установка обработчиков нажатий для кнопок
        setButtonListeners();
    }

    // Метод для инициализации элементов интерфейса
    private void initializeViews() {
	
	// Сложение, вычитание, деление, умножение
        buttonAdd = findViewById(R.id.buttonAdd); 
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiply = findViewById(R.id.buttonMultiply);

	// Кнопка очистки
        buttonClean = findViewById(R.id.buttonClean); 

	// Кнопка возведения в степень
        buttonStepen = findViewById(R.id.buttonStepen);

        operation = findViewById(R.id.operation); // Текстовое поле для отображения операции

	// Поля для двух чисел 
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
	
	// Поле для отображения результата
        result = findViewById(R.id.result); 
    }

    // Метод для установки обработчиков событий нажатия на кнопки
    private void setButtonListeners() {

	// Обработчик для сложения, вычитания, умножения, деления
        buttonAdd.setOnClickListener(v -> calculate("+")); 
        buttonSubtract.setOnClickListener(v -> calculate("-"));
        buttonMultiply.setOnClickListener(v -> calculate("*"));
        buttonDivide.setOnClickListener(v -> calculate("/")); 

	// Обработчик для кнопки возведения в степень
        buttonStepen.setOnClickListener(v -> calculate("^")); 

	// Обработчик для кнопки очистки чисел
        buttonClean.setOnClickListener(v -> clearFields()); 
    }

    // Метод для выполнения вычислений
    private void calculate(String op) {
	
	// Проверка на заполненость числами
        if (isInputValid()) { 

	    // Преобразование в double первого числа
            double num1 = Double.parseDouble(number1.getText().toString()); 

	    // Преобразование в Преобразование в double второго числа
            double num2 = Double.parseDouble(number2.getText().toString());

            double res; 

            // Выполнение операции в зависимости от выбранного знака
            switch (op) {

		// Сложение
                case "+":
                    operation.setText("+"); 
                    res = num1 + num2; 
                    break;

		// Вычитание
                case "-":
                    operation.setText("-");
                    res = num1 - num2; 
                    break;

		// Умножение 
                case "*":
                    operation.setText("*"); 
                    res = num1 * num2; 
                    break;

		// Деление
                case "/":
                    operation.setText("/");
                    if (num2 != 0) { 
                        res = num1 / num2; 
                    } else {
                        showError("Ошибка: деление на ноль"); 
                        return ;
                    }
                    break;

		// Возведение в степень
                case "^": 
                    operation.setText("^"); 
                    res = Math.pow(num1, num2);
                    break;

                default:
                    showError("Ошибка: неверная операция"); 
                    return ;
            }
            result.setText(String.valueOf(res));
        } else {
            showError("Ошибка: введите числа"); 
        }
    }

    // Метод для проверки корректности ввода чисел
    private boolean isInputValid() {
	// Возвращает true если поля не пустые
        return !TextUtils.isEmpty(number1.getText())
                &&
                !TextUtils.isEmpty(number2.getText());
    }

    // Метод для очистки полей ввода и результата
    private void clearFields() {

	// Очистка первого и второго поля
        number1.setText(""); 
        number2.setText(""); 

	// Очистка знака операции
        operation.setText(""); 

	// Очистка результата
        result.setText(""); // Очистка результата
    }

    // Метод для отображения сообщений об ошибках
    private void showError(String message) {

	// Всплывающее окно с информацией
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); 
    }
}
