from django.urls import path

from . import views

app_name = 'dxc'

urlpatterns = [
    path('', views.index, name='index'),
    path('retiro/', views.retiro, name='retiro')
]