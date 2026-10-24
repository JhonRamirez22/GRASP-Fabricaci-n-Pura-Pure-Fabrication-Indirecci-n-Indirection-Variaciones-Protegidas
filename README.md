# Patrones GRASP en un caso de supermercado

Este proyecto muestra tres patrones GRASP aplicados al mismo caso: cobrar una venta y avisar al cliente.
Para cada patrón hay una versión **sin patrón** (el problema) y una **con patrón** (la solución), en Java y con POO.

Los patrones GRASP (*General Responsibility Assignment Software Patterns*) no son recetas para crear clases.
Son criterios para decidir **quién debe hacer qué** en un diseño orientado a objetos, de forma que el sistema
sea fácil de entender, cambiar y probar.

Los dos principios que aparecen en los tres casos son:

- **Alta cohesión:** cada clase tiene responsabilidades relacionadas entre sí.
- **Bajo acoplamiento:** una clase depende lo menos posible de los detalles de otras.

---

## Resumen

| Patrón | Problema de diseño que resuelve | Solución en este caso | Costo o riesgo |
|---|---|---|---|
| **Fabricación Pura** | Una clase del dominio hace trabajo técnico (guardar datos) que no le corresponde. Pierde cohesión. | `RepositorioVentas` guarda las ventas. `Venta` solo conoce su dominio. | Se crean clases que no existen en el mundo real. Si se abusa, aparecen clases sin sentido. |
| **Indirección** | `Caja` conoce los detalles de un canal de envío (servidor, puerto). Está muy acoplada a ellos. | `Notificador` se pone entre `Caja` y el servicio de correo. | Una capa más que recorrer. Si hay demasiados intermediarios, el flujo es difícil de seguir. |
| **Variaciones Protegidas** | Agregar un canal (WhatsApp) obliga a modificar clases existentes. | Interfaz `Notificador` como punto de variación estable. Cada canal es una clase que la implementa. | Más clases e interfaces. Conviene solo cuando el cambio es previsible. |

---

## 1. Fabricación Pura (Pure Fabrication)

**Definición:** crear una clase que no representa un concepto del dominio, para organizar una responsabilidad
que no encaja bien en ninguna clase del dominio.

### Por qué existe

Una regla práctica de GRASP dice que la responsabilidad debe ir a la clase que tiene la información necesaria
(*Experto en información*). Pero a veces esa clase no debería ser la que hace el trabajo técnico.
En este caso, `Venta` sabe calcular su total, pero guardarse en una base de datos no es parte del concepto de venta.

### Problema (sin patrón)

[p15-sin-patron](p15-sin-patron)

- `Venta` calcula el total **y** se guarda a sí misma con `guardar()`.
- Si cambia la base de datos, hay que modificar la clase `Venta`, que es un concepto del negocio.
- `Venta` pierde cohesión: tiene dos motivos distintos para cambiar.

### Solución (con patrón)

[p15-bien](p15-bien)

- `Venta` solo calcula su total y gestiona sus productos.
- `RepositorioVentas` es una **Fabricación Pura**: no existe en el supermercado real, pero concentra el acceso a datos.
- Cambiar el almacenamiento solo afecta a `RepositorioVentas`.

### Cuándo usarlo

- Cuando una responsabilidad técnica (persistencia, impresión, envío de correos, cálculos de infraestructura)
  no encaja en ninguna clase del dominio.
- Cuando quieres que el dominio no dependa de la tecnología.

### Cuándo no usarlo

- Cuando la responsabilidad sí pertenece a una clase del dominio. Crear una clase por crear solo agrega complejidad.

---

## 2. Indirección (Indirection)

**Definición:** introducir un intermediario entre dos clases para que no se conozcan directamente.

### Por qué existe

Si una clase llama directamente a otra con todos sus detalles, cualquier cambio en la segunda obliga a cambiar la primera.
Un intermediario absorbe esos cambios.

### Problema (sin patrón)

[p16-sin-patron](p16-sin-patron)

- `Caja` crea un `ServicioCorreo` y conoce su host (`smtp.supermercado.com`) y su puerto (`587`).
- Si cambia el servidor de correo, hay que modificar `Caja`, aunque el cobro no tenga nada que ver con el correo.
- `Caja` queda acoplada a un detalle técnico.

### Solución (con patrón)

[p16-con-patron](p16-con-patron)

- `Notificador` actúa como **intermediario**. Crea y contiene el `ServicioCorreo`.
- `Caja` solo llama a `notificarCompra(...)`. No sabe qué servidor se usa.
- Cambiar el servidor afecta solo a `Notificador`.

### Cuándo usarlo

- Cuando dos clases deben colaborar, pero una no debería conocer los detalles de la otra.
- Cuando anticipas que el lado técnico (servidores, proveedores, APIs externas) puede cambiar.

### Cuándo no usarlo

- Cuando el intermediario no oculta nada y solo agrega un paso más en el flujo.

---

## 3. Variaciones Protegidas (Protected Variations)

**Definición:** identificar los puntos donde el sistema puede cambiar y protegerlos detrás de una interfaz estable.
Las clases que usan esos puntos dependen de la interfaz, no de cada variante.

### Por qué existe

Si una clase decide cómo actuar comparando valores (`if canal == "EMAIL"`, `else if canal == "SMS"`), cada variante nueva
obliga a modificar esa clase. Eso rompe el principio abierto/cerrado: el sistema debe poder extenderse sin modificar lo existente.

### Problema (sin patrón)

[p17-sin-patron](p17-sin-patron)

- `Notificador` decide el canal con `if/else` sobre cadenas de texto.
- Agregar WhatsApp obliga a modificar `Notificador`.
- `Caja` depende de la clase concreta y de códigos de texto (`"EMAIL"`, `"SMS"`), que se pueden escribir mal.

### Solución (con patrón)

[p17-con-patron](p17-con-patron)

- La interfaz `Notificador` define el contrato: `enviar(destino, mensaje)`.
- Cada canal es una clase que la implementa: `NotificadorEmail`, `NotificadorSMS`, `NotificadorWhatsApp`.
- `Caja` recibe un `Notificador` por constructor. No cambia cuando aparece un canal nuevo.

### Cuándo usarlo

- Cuando sabes que habrá varias variantes de un mismo comportamiento (canales de envío, formas de pago, tipos de descuento).
- Cuando el cambio es previsible y repetido.

### Cuándo no usarlo

- Cuando solo hay una variante y no hay señales de que vaya a cambiar. Una interfaz para una sola implementación es complejidad innecesaria.

### Relación con otros patrones de diseño

Este caso se parece al patrón **Strategy** de GoF: el comportamiento (cómo notificar) se encapsula en objetos intercambiables.

---

## Cómo se relacionan los tres

1. **Fabricación Pura** separa la responsabilidad técnica de la venta.
2. **Indirección** pone un intermediario entre la caja y el canal de envío.
3. **Variaciones Protegidas** convierte ese intermediario en una interfaz estable, para que los canales puedan crecer sin tocar la caja.

En el caso final, `Caja` no sabe si el aviso sale por correo, SMS o WhatsApp, ni cómo se guarda la venta.

---

## Estructura del proyecto

| Carpeta | Contenido |
|---|---|
| [p15-sin-patron](p15-sin-patron) | Patrón 15 sin aplicar: `Venta` con `guardar()` |
| [p15-bien](p15-bien) | Patrón 15 aplicado: `RepositorioVentas` como Fabricación Pura |
| [p15-mal](p15-mal) | Ejemplo de errores comunes en el diagrama (interfaz mal usada, clases abstractas, tipos mal escritos). No compila |
| [p16-sin-patron](p16-sin-patron) | Patrón 16 sin aplicar: `Caja` crea el `ServicioCorreo` |
| [p16-con-patron](p16-con-patron) | Patrón 16 aplicado: `Notificador` como intermediario |
| [p17-sin-patron](p17-sin-patron) | Patrón 17 sin aplicar: `Notificador` con `if/else` por canal |
| [p17-con-patron](p17-con-patron) | Patrón 17 aplicado: interfaz `Notificador` y una clase por canal |
| [vp-base](vp-base) | Clases base `Venta` y `Producto` para el diagrama de Visual Paradigm |
| [diagramas](diagramas) | Archivos Mermaid de la versión sencilla de los diagramas |
| [GUIA.md](GUIA.md) | Especificación de clases, atributos, operaciones y relaciones para dibujar en Visual Paradigm |

Las carpetas `p15`, `p16` y `p17` (con subcarpetas `problema` y `solucion`) son la versión anterior, más simple, del ejemplo.

## Cómo compilar y ejecutar

Desde esta carpeta, cada versión se compila y se ejecuta por separado:

```bash
javac -d bin/p15-sin-patron p15-sin-patron/*.java && java -cp bin/p15-sin-patron Main
```

Cambia el nombre de la carpeta por cualquiera de la tabla anterior (`p15-bien`, `p16-con-patron`, etc.).
