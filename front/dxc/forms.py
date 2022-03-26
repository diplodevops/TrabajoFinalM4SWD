from django import forms

class DxcForm(forms.Form):
    ahorro = forms.CharField(max_length = 20)
    sueldo = forms.CharField(max_length = 20)