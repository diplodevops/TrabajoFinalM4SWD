from curses.ascii import HT
from django.shortcuts import render
from .forms import DxcForm

from .utils import get_dxc

def index(request):
    context = {}    
    return render(request, 'dxc/index.html', context)


def retiro(request):
    answered = False
    dxc = None
    tipo = None

    if request.method == 'POST':
        dxc_form = DxcForm(data=request.POST)

        if dxc_form.is_valid():
            sueldo = int(dxc_form.cleaned_data['sueldo'])
            ahorro = int(dxc_form.cleaned_data['ahorro'])
            tipo =  dxc_form.cleaned_data['tipo']

            dxc = get_dxc(sueldo, ahorro)
            answered = True
    else:
        dxc_form = DxcForm()

    context = {
        'dxc_form': dxc_form,
        'answered': answered,
        'dxc': dxc,
        'tipo': tipo
    }  
    return render(request, 'dxc/retiro.html', context)
