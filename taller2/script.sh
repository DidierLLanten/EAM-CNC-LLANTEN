variable=${1}

mensajes() {
	echo "Hackeando la nasa.............."
	echo "Accediendo a los sistemas ....."
	echo "Obteniendo informacion bancaria"
	echo "EL valor de la variable es" $variable
}

fecha() {
	echo "Obteniendo la fecha"
	date
}

#Documentacion
#leer archivo de los usuarios
	#cat /etc/passwd

mensajes
fecha
