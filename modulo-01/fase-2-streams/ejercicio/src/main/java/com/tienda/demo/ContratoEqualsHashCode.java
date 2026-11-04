package com.tienda.demo;

import java.util.HashMap;

/**
 * Demo en directo: qué pasa cuando se rompe el contrato equals/hashCode.
 * Analogía: hashCode dice en qué cajón buscar; equals, qué objeto es dentro del cajón.
 */
public class ContratoEqualsHashCode {

    static class ClienteSinHashCode {
        private final String id;

        ClienteSinHashCode(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof ClienteSinHashCode otro && id.equals(otro.id);
        }
        // hashCode NO sobrescrito: se usa el de Object, distinto para cada instancia.
    }

    static class ClienteMutable {
        private String id;

        ClienteMutable(String id) {
            this.id = id;
        }

        void setId(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof ClienteMutable otro && id.equals(otro.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    record ClienteRecord(String id) {}

    public static void main(String[] args) {
        System.out.println("== 1. equals sin hashCode ==");
        var puntos = new HashMap<ClienteSinHashCode, Integer>();
        puntos.put(new ClienteSinHashCode("C1"), 100);
        var mismoCliente = new ClienteSinHashCode("C1");
        System.out.println("¿Son iguales?: " + new ClienteSinHashCode("C1").equals(mismoCliente));
        System.out.println("get(C1): " + puntos.get(mismoCliente));

        System.out.println("== 2. Clave mutable ==");
        var cliente = new ClienteMutable("C1");
        var pedidosPorCliente = new HashMap<ClienteMutable, Integer>();
        pedidosPorCliente.put(cliente, 5);
        cliente.setId("C99");
        System.out.println("get(cliente): " + pedidosPorCliente.get(cliente));
        System.out.println("size(): " + pedidosPorCliente.size());

        System.out.println("== 3. Con un record ==");
        var conRecord = new HashMap<ClienteRecord, Integer>();
        conRecord.put(new ClienteRecord("C1"), 100);
        System.out.println("get(C1): " + conRecord.get(new ClienteRecord("C1")));
    }
}
