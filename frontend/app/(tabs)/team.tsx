import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  FlatList,
  ActivityIndicator,
  TouchableOpacity,
  Alert,
} from 'react-native';
import { useFocusEffect } from '@react-navigation/native';

// 🔧 Spielertypen definieren
type Player = {
  id: number;
  name: string;
  playerType: string;
  points?: number;
  price: number;
};

export default function TeamScreen() {
  const [players, setPlayers] = useState<Player[]>([]);
  const [budget, setBudget] = useState<number | null>(null);
  const [loading, setLoading] = useState(true);

  const userId = 1;
  const teamId = 1;

  useFocusEffect(
    React.useCallback(() => {
      setLoading(true);
      fetch(`http://192.168.198.1:8080/api/team/byUser/${userId}`)
        .then(res => res.json())
        .then(data => {
          console.log("📥 Teamdaten geladen:", data);
          setPlayers(data.players || []);
          setBudget(data.user?.budget || null);
          setLoading(false);
        })
        .catch(err => {
          console.error('❌ Fehler beim Laden:', err);
          setLoading(false);
        });
    }, [])
  );

  const handleSell = (playerId: number) => {
    fetch(`http://192.168.198.1:8080/api/team/${teamId}/sellPlayer/${playerId}`, {
      method: 'DELETE',
    })
      .then(res => {
        if (res.ok) {
          Alert.alert("✅ Spieler verkauft!");
          const soldPlayer = players.find(p => p.id === playerId);
          setPlayers(prev => prev.filter(p => p.id !== playerId));
          if (soldPlayer && soldPlayer.price && budget !== null) {
            setBudget(budget + soldPlayer.price);
          }
        } else {
          Alert.alert("❌ Fehler beim Verkauf");
        }
      })
      .catch(() => {
        Alert.alert("❌ Netzwerkfehler beim Verkauf");
      });
  };

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#ff9500" />
        <Text>Lade dein Team...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>🏐 Mein Team</Text>
      {budget !== null && (
        <Text style={styles.budget}>💶 Budget: {budget.toLocaleString()} €</Text>
      )}
      <FlatList
        data={players}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <Text style={styles.name}>{item.name}</Text>
            <Text style={styles.role}>Position: {item.playerType}</Text>
            <Text style={styles.points}>
              Punkte: {item.points !== undefined ? item.points : '–'}
            </Text>
            <Text style={styles.price}>💰 Wert: {item.price.toLocaleString()} €</Text>
            <TouchableOpacity
              style={styles.sellButton}
              onPress={() => handleSell(item.id)}
            >
              <Text style={styles.buttonText}>Spieler verkaufen</Text>
            </TouchableOpacity>
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#fff' },
  title: { fontSize: 24, fontWeight: 'bold', marginBottom: 10, textAlign: 'center' },
  budget: {
    fontSize: 16,
    fontWeight: '600',
    textAlign: 'center',
    marginBottom: 20,
    color: '#007700',
  },
  card: {
    padding: 15,
    backgroundColor: '#f1f1f1',
    marginBottom: 12,
    borderRadius: 8,
  },
  name: { fontSize: 18, fontWeight: 'bold' },
  role: { fontSize: 14, color: '#555' },
  points: { fontSize: 14, color: '#111' },
  price: { fontSize: 14, color: '#444', marginTop: 4 },
  sellButton: {
    backgroundColor: '#ff3b30',
    paddingVertical: 8,
    borderRadius: 6,
    alignItems: 'center',
    marginTop: 10,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
});
