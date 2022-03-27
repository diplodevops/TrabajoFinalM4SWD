from django import forms


INPUT_CHOICES =(
    ("retiro", "Retiro 10%"),
    ("saldo", "Saldo Restante"),
    ("impuesto", "Impuesto")
)

class DxcForm(forms.Form):
    ahorro = forms.CharField(max_length = 20)
    sueldo = forms.CharField(max_length = 20)
    tipo = forms.ChoiceField(choices=INPUT_CHOICES)